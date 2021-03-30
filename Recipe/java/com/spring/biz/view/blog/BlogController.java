package com.spring.biz.view.blog;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.UploadContext;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.biz.blog.BlogImageVO;
import com.spring.biz.blog.BlogService;
import com.spring.biz.blog.BlogVO;
import com.spring.biz.member.MemberVO;

@Controller
public class BlogController {
	private static final String BLOG_IMAGE_REPO = "C:\\gallery\\bfile_repo";

	@Autowired
	private BlogService blogService;
	
	public BlogController() {
		System.out.println("===BlogController() 객체 생성===");
	}
	
	@RequestMapping(value = "/insertBlog.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity insertBlog(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) 
			throws Exception {
		System.out.println("===Controller의 insertBlog() 실행===");
		
		Map blogMap = new HashMap();
		Enumeration enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			System.out.println(name + " : " + value);
			blogMap.put(name, value);
		}
		
		HttpSession sess = multipartRequest.getSession();
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		blogMap.put("written", mvo.getNickname());
		blogMap.put("id", mvo.getId());
		
		
		List<String> bfileList = upload(multipartRequest);
		List<BlogImageVO> bimageFileList = new ArrayList<BlogImageVO>();
		if(bfileList != null && bfileList.size() != 0) {
			for(String bfileName : bfileList) {
				BlogImageVO bimageVO = new BlogImageVO();
				bimageVO.setImageFileName(bfileName);
				bimageFileList.add(bimageVO);
			}
			
			blogMap.put("bimageFileList", bimageFileList);
		}
		
		String message = new String();
		
		try {
			int blogno = blogService.insertBlog(blogMap);
			if(bimageFileList!=null && bimageFileList.size()!=0) {
				for(BlogImageVO bimageVO : bimageFileList) {
					String bimageFileName = bimageVO.getImageFileName();
					File srcFile = new File(BLOG_IMAGE_REPO + "\\" + "temp" + "\\" + bimageFileName);
					File destDir = new File(BLOG_IMAGE_REPO + "\\" + blogno);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
			}
			message = "<script>";
			message += " alert('글이 등록되었습니다.');";
			message += " location.href = '" + multipartRequest.getContextPath() + "/getBlog.do?blogno=" + blogno +"';";
			message += ("</script>");
			
		}catch(Exception e) {
			if(bimageFileList!=null && bimageFileList.size()!=0) {
				for(BlogImageVO bimageVO : bimageFileList) {
					String bimageFileName = bimageVO.getImageFileName();
					File srcFile = new File(BLOG_IMAGE_REPO + "\\" + "temp" + "\\" + bimageFileName);
					srcFile.delete();
				}
			}
			message= "<script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message +=" location.href='" + multipartRequest.getContextPath() + "/getBlogList.do';";
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
			File file = new File(BLOG_IMAGE_REPO + "\\" + fileName);
			if(mFile.getSize()!=0) {
				if(! file.exists()) {
					if(file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(BLOG_IMAGE_REPO + "\\" + "temp"
											+ "\\" + originalFileName));
			}
		}
		return fileList;
	}
	
	
	@RequestMapping(value = "/updateBjsp.do", method=RequestMethod.GET)
	public String updateBjsp(@RequestParam int blogno, Model model, HttpSession sess) {
		System.out.println("===Controller의 updateBjsp() 실행===");
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String logid = mvo.getId();
		String blogid = blogService.idChk(blogno);
		if(logid.equals(blogid)) {
			BlogVO bvo = blogService.getBlog(blogno);
			model.addAttribute("blog", bvo);
			return "blogUpdate";
		}else {
			return "redirect:blogSingle";
		}
	}
	
	@RequestMapping(value = "/updateBlog.do")
	public String updateBlog(BlogVO bvo, Model model) {
		System.out.println("===Controller의 updateBlog() 실행===");
		System.out.println("bvo : " + bvo);
		int result = blogService.updateBlog(bvo);
		BlogVO blog = blogService.getBlog(bvo.getBlogno());
		model.addAttribute("blog", blog);
		return "blogSingle";
	}
	
	@RequestMapping(value = "/deleteBlog.do", method=RequestMethod.GET)
	public String deleteBlog(@RequestParam int blogno, HttpSession sess) {
		System.out.println("===Controller의 deleteBlog() 실행===");
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String logid = mvo.getId();
		String blogid = blogService.idChk(blogno);
		if(logid.equals(blogid)) {
			blogService.deleteBlog(blogno);
			return "Blog";
		}else {
			return "redirect:blogSingle";
		}
	}
	
	@RequestMapping(value = "/getBlogList.do")
	public String getBlogList(Model model) {
		System.out.println("===Controller의 getBlogList() 실행===");
		List<BlogVO> list = blogService.getBlogList();
		model.addAttribute("blogList", list);
		return "Blog";
	}
	
	@RequestMapping(value = "/getMyblogList.do")
	public String getMyblogList(Model model, HttpSession sess) {
		System.out.println("===Controller의 getMyblogList() 실행===");
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String id = mvo.getId();
		List<BlogVO> list = blogService.getMyblogList(id);
		model.addAttribute("myblogList", list);
		return "myBlog";
	}
	
	@RequestMapping(value = "/getBlog.do", method=RequestMethod.GET)
	public String getBlog(@RequestParam int blogno, Model model) {
		System.out.println("===Controller의 getBlog() 실행===");
		BlogVO bvo = blogService.getBlog(blogno);
		model.addAttribute("blog", bvo);
		return "blogSingle";
	}
	
}