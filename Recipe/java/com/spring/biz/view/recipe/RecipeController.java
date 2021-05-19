package com.spring.biz.view.recipe;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.biz.member.MemberVO;
import com.spring.biz.paging.PagingVO;
import com.spring.biz.recipe.RecipeImageVO;
import com.spring.biz.recipe.RecipeService;
import com.spring.biz.recipe.RecipeVO;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@SessionAttributes("recipe")
public class RecipeController {
	private static final String RECIPE_IMAGE_REPO = "C:\\gallery\\rfile_repo";

	@Autowired
	private RecipeService recipeService;
	private PagingVO pagingVO;
	
	public RecipeController() {
		System.out.println("===RecipeController() 객체 생성===");
	}
	
	@RequestMapping(value = "/insertRecipe.do", method=RequestMethod.POST)
	@ResponseBody
	public String insertRecipe(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, Model model) 
			throws Exception {
		System.out.println("===Controller의 insertRecipe() 실행===");
		
		String rimageFileName = null;
		
		Map recipeMap = new HashMap();
		Enumeration enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			System.out.println(name + " : " + value);
			recipeMap.put(name, value);
		}
		
		HttpSession sess = multipartRequest.getSession();
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		recipeMap.put("written", mvo.getNickname());
		recipeMap.put("id", mvo.getId());
		
		
		List<String> rfileList = upload(multipartRequest);
		List<RecipeImageVO> rimageFileList = new ArrayList<RecipeImageVO>();
		
		if(rfileList != null && rfileList.size() != 0) {
			for(String rfileName : rfileList) {
				RecipeImageVO rimageVO = new RecipeImageVO();
				rimageVO.setRimageFileName(rfileName);
				rimageFileList.add(rimageVO);
			}
			
			recipeMap.put("rimageFileList", rimageFileList);
		}
		
		String message = null;
		int recipeno = 0;
		
		try {
			recipeno = recipeService.insertRecipe(recipeMap);
			if(rimageFileList!=null && rimageFileList.size()!=0) {
				for(RecipeImageVO rimageVO : rimageFileList) {
					rimageFileName = rimageVO.getRimageFileName();
					File srcFile = new File(RECIPE_IMAGE_REPO + "\\" + "temp" + "\\" + rimageFileName);
					File destDir = new File(RECIPE_IMAGE_REPO + "\\" + recipeno);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
			}
			message = "<script>";
			message += " alert('레시피가 등록되었습니다.');";
			message += " location.href = '" + multipartRequest.getContextPath() + "/getRecipe.do?recipeno=" + recipeno +"';";
			message += ("</script>");
		}catch(Exception e) {
			if(rimageFileList!=null && rimageFileList.size()!=0) {
				for(RecipeImageVO rimageVO : rimageFileList) {
					rimageFileName = rimageVO.getRimageFileName();
					File srcFile = new File(RECIPE_IMAGE_REPO + "\\" + "temp" + "\\" + rimageFileName);
					srcFile.delete();
				}
			}
			message= "<script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message +=" location.href='" + multipartRequest.getContextPath() + "/getRecipeList.do';";
			message +=("</script>");
			e.printStackTrace();
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		ResponseEntity resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		List<RecipeVO> list = recipeService.getRecipeList();
		model.addAttribute("recipeList", list);
		return "Recipe";
	}
	
	private List<String> upload(MultipartHttpServletRequest multipartRequest)
			throws Exception{
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			fileList.add(originalFileName);
			File file = new File(RECIPE_IMAGE_REPO + "\\" + fileName);
			if(mFile.getSize()!=0) {
				if(! file.exists()) {
					if(file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(RECIPE_IMAGE_REPO + "\\" + "temp"
											+ "\\" + originalFileName));
			}
		}
		return fileList;
	}
	
	@RequestMapping(value = "/writeRecipe.do")
	public String writeRecipe() {
		return "recipeWrite";
	}
	
	@RequestMapping(value = "/updateRjsp.do", method=RequestMethod.GET)
	public String updateRjsp(@RequestParam("recipeno") int recipeno, Model model, HttpSession sess) {
		System.out.println("===Controller의 updateRjsp() 실행===");
		//로그인 안 한 상태에서 수정 누르면 바로 널포인트익셉션 에러
		//if문으로 로그인/아웃 나눠서 안내문 띄우거나 해야함
		// ===> jsp에서 로그아웃 상태면 아예 수정 버튼 띄우지 않음 & 현재 로그인한 아이디와 해당 글의 아이디 일치하는 경우에만 수정 버튼 나타남 
//		MemberVO mvo = (MemberVO) sess.getAttribute("member");
//		System.out.println(mvo.getId());
//		String logid = mvo.getId();
//		String recipeid = recipeService.idChk(recipeno);
//		if(logid.equals(recipeid)) {
//			RecipeVO rvo = recipeService.getRecipe(recipeno);
//			model.addAttribute("recipe", rvo);
//			return "recipeUpdate";
//		}else {
//			return "redirect:recipeSingle";
//		}
		
		RecipeVO rvo = recipeService.getRecipe(recipeno);
		model.addAttribute("recipe", rvo);
		return "recipeUpdate";
	}
	
	@RequestMapping(value = "/updateRecipe.do")
	public String updateRecipe(RecipeVO rvo, Model model) {
		System.out.println("===Controller의 updateRecipe() 실행===");
		System.out.println("rvo : " + rvo);
		int result = recipeService.updateRecipe(rvo);
		RecipeVO recipe = recipeService.getRecipe(rvo.getRecipeno());
		model.addAttribute("recipe", recipe);
		return "recipeSingle";
	}
	
	@RequestMapping(value = "/deleteRecipe.do", method=RequestMethod.GET)
	public String deleteRecipe(int recipeno, Model model, HttpSession sess) {
		//파라미터에 @ModelAttribute("member") MemberVO mvo 넣었는데 작동 안 함
		System.out.println("===Controller의 deleteRecipe() 실행===");
//		MemberVO mvo = (MemberVO) sess.getAttribute("member");
//		System.out.println(mvo.getId());
//		String logid = mvo.getId();
//		String recipeid = recipeService.idChk(recipeno);
//		System.out.println(recipeid);
//		if(logid.equals(recipeid)) {
//			recipeService.deleteRecipe(recipeno);
//			return "redirect:/getRecipeList.do";
//		}else {
//			return "redirect:recipeSingle";
//		}
		System.out.println("레시피no : " + recipeno);
		recipeService.deleteRecipeimg(recipeno);
		recipeService.deleteLike(recipeno);
		recipeService.deleteRecipe(recipeno);
		return "redirect:/recipePaging.do?category=";
	}
	
	@RequestMapping(value = "/getRecipeList.do")
	public String getRecipeList(Model model) {
		System.out.println("===Controller의 getRecipeList() 실행===");
		List<RecipeVO> list = recipeService.getRecipeList();
		System.out.println("리스트 원소 개수 : " + list.size());
		model.addAttribute("recipeList", list);
		return "Recipe";
	}
	
	@RequestMapping(value = "/getMyrecipeList.do")
	public String getMyrecipeList(Model model, HttpSession sess) {
		System.out.println("===Controller의 getMyrecipeList() 실행===");
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String id = mvo.getId();
		List<RecipeVO> list = recipeService.getMyrecipeList(id);
		model.addAttribute("myrecipeList", list);
		return "myRecipe";
	}
	
	@RequestMapping(value = "/getRecipe.do", method=RequestMethod.GET)
	public String getRecipe(@RequestParam int recipeno, 
			@RequestParam(value="category", required=false)String category, Model model, HttpSession sess) {
		System.out.println("===Controller의 getRecipe() 실행===");
		System.out.println("직전 카테고리 : " + category);
		model.addAttribute("category", category);
		RecipeVO rvo = recipeService.getRecipe(recipeno);
		model.addAttribute("recipe", rvo);
		List<RecipeImageVO> rimageFileList = recipeService.getRimageList(recipeno);
		model.addAttribute("rimageList", rimageFileList);
		return "recipeSingle";
	}
	
	@RequestMapping(value = "/rdownload.do", method = RequestMethod.GET)
	public void rdownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("레시피 이미지 download!!!");
		String recipeno = request.getParameter("recipeno");
		String filename = request.getParameter("rfilename");
		System.out.println(recipeno + "&" + filename);
		
		OutputStream out = response.getOutputStream();
		String filepath = RECIPE_IMAGE_REPO + "\\" + recipeno + "\\" + filename;
		File rimage = new File(filepath);
		if(rimage.exists()) {
			System.out.println(rimage.getName());
		}
		
		response.setHeader("Cache-Control","no-cache");
		response.addHeader("Content-disposition", "attachment; filename="+filename);
		FileInputStream in = new FileInputStream(rimage); 
		byte[] buffer=new byte[1024*8];
		while(true){
			int count=in.read(buffer);
			if(count==-1)  
				break;
			out.write(buffer,0,count);
		}
		
		in.close();
		out.close();
	}
	
	@RequestMapping(value = "/rthumbnails.do", method = RequestMethod.GET)
	public void rthumbnails(int recipeno, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("레시피 이미지 썸네일!!!");
		System.out.println(recipeno);
//		List<String> fileList = recipeService.getFileName(recipeno);
//		String filename = null;
//		filename = (String) fileList.get(0);
		
		String filename = recipeService.getFileName(recipeno);
		System.out.println(filename);
		
		OutputStream out = response.getOutputStream();
		String filepath = RECIPE_IMAGE_REPO + "\\" + recipeno + "\\" + filename;
		
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		
		File rimage=new File(filepath);
		System.out.println(rimage.exists());
		if (rimage.exists()) { 
			System.out.println(rimage.getName());
			BufferedImage buffimage = Thumbnails.of(rimage).size(190,190).outputFormat("jpeg").asBufferedImage();
			
			ImageIO.write(buffimage, "jpeg", jpegOutputStream);
		}
		byte[] imgByte = jpegOutputStream.toByteArray();
		
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		out.write(imgByte);
		out.flush();
		out.close();
	}
	
	@RequestMapping(value = "/recipePaging.do")
	public String recipePaging(Model model,
			@RequestParam(value="nowPage", required=false)String nowPage, 
			@RequestParam(value="category", required=false)String category) {
		System.out.println("===Controller의 recipePaging() 실행===");
		if(category.equals("")) {
		int total = recipeService.countTotal();
		if(nowPage==null) {nowPage = "1";}
		
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), category);
		List<RecipeVO> list = recipeService.selectRecipe(pagingVO);
		model.addAttribute("recipeList", list);
		model.addAttribute("paging", pagingVO);
		
		System.out.println("startPage : " + pagingVO.getStartPage() + ", endPage : " + pagingVO.getEndPage());
		System.out.println("lastPage : " + pagingVO.getLastPage());
		System.out.println("start : " + pagingVO.getStart() + ", end : " + pagingVO.getEnd());
		System.out.println("prev : " + pagingVO.getPrev() + ", prevPage : " + pagingVO.getPrevPage());
		System.out.println("next : " + pagingVO.getNext() + ", nextPage : " + pagingVO.getNextPage());
		return "Recipe";
		}else {
			System.out.println("현재 카테고리 : " + category);
			
			if(category.equals("korean")) {
				category = "한식";
			}else if(category.equals("western")) {
				category = "양식";
			}else if(category.equals("japanese")) {
				category = "일식";
			}else if(category.equals("chinese")) {
				category = "중식";
			}else if(category.equals("bread")) {
				category = "제과제빵";
			}else if(category.equals("drink")) {
				category = "음료";
			}
			
			int total = recipeService.countCategoryTotal(category);
			if(nowPage==null) {nowPage = "1";}
			pagingVO = new PagingVO(total, Integer.parseInt(nowPage), category);
			List<RecipeVO> list = recipeService.selectCategory(pagingVO);
			
			model.addAttribute("recipeList", list);
			model.addAttribute("paging", pagingVO);
			return "Recipe";
		}
	}
	
	@RequestMapping(value = "/searchRecipe.do")
	public String searchRecipe(Model model, 
			@RequestParam(value="keyword", required=false)String keyword, 
			@RequestParam(value="nowPage", required=false)String nowPage) {
		int total = recipeService.countSearchTotal(keyword);
		if(nowPage==null) {nowPage="1";}
		pagingVO = new PagingVO(keyword, total, Integer.parseInt(nowPage));
		List<RecipeVO> list = recipeService.searchRecipe(pagingVO);
		
		model.addAttribute("recipeList", list);
		model.addAttribute("paging", pagingVO);
		return "Recipe";
	}

	
}