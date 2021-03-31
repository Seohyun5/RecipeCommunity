package com.spring.biz.view.recipe;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.biz.blog.BlogImageVO;
import com.spring.biz.blog.BlogVO;
import com.spring.biz.member.MemberVO;
import com.spring.biz.recipe.RecipeImageVO;
import com.spring.biz.recipe.RecipeService;
import com.spring.biz.recipe.RecipeVO;

@Controller
public class RecipeController {
	private static final String RECIPE_IMAGE_REPO = "C:\\gallery\\rfile_repo";

	@Autowired
	private RecipeService recipeService;
	
	public RecipeController() {
		System.out.println("===RecipeController() 객체 생성===");
	}
	
	@RequestMapping(value = "/insertRecipe.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity insertRecipe(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) 
			throws Exception {
		System.out.println("===Controller의 insertRecipe() 실행===");
		
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
				rimageVO.setImageFileName(rfileName);
				rimageFileList.add(rimageVO);
			}
			
			recipeMap.put("rimageFileList", rimageFileList);
		}
		
		String message = new String();
		
		try {
			int recipeno = recipeService.insertRecipe(recipeMap);
			if(rimageFileList!=null && rimageFileList.size()!=0) {
				for(RecipeImageVO rimageVO : rimageFileList) {
					String rimageFileName = rimageVO.getImageFileName();
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
					String rimageFileName = rimageVO.getImageFileName();
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
		return resEnt;
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
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String logid = mvo.getId();
		String recipeid = recipeService.idChk(recipeno);
		if(logid.equals(recipeid)) {
			RecipeVO rvo = recipeService.getRecipe(recipeno);
			model.addAttribute("recipe", rvo);
			return "recipeUpdate";
		}else {
			return "redirect:recipeSingle";
		}
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
	public String deleteRecipe(int recipeno, @ModelAttribute("member") MemberVO mvo) {
		System.out.println("===Controller의 deleteRecipe() 실행===");
//		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String logid = mvo.getId();
		String recipeid = recipeService.idChk(recipeno);
		if(logid.equals(recipeid)) {
			recipeService.deleteRecipe(recipeno);
			return "Recipe";
		}else {
			return "redirect:recipeSingle";
		}
	}
	
	@RequestMapping(value = "/getRecipeList.do")
	public String getRecipeList(Model model) {
		System.out.println("===Controller의 getRecipeList() 실행===");
		List<RecipeVO> list = recipeService.getRecipeList();
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
	public String getRecipe(@RequestParam int recipeno, Model model) {
		System.out.println("===Controller의 getRecipe() 실행===");
		RecipeVO rvo = recipeService.getRecipe(recipeno);
		model.addAttribute("recipe", rvo);
		return "recipeSingle";
	}
}