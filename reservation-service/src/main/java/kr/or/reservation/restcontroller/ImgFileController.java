package kr.or.reservation.restcontroller;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.reservation.common.FileRead;
import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.service.CommentService;
import kr.or.reservation.service.ImgFileService;


@RestController
@RequestMapping("/api/img")
public class ImgFileController {

	
	ImgFileService imgService;
	CommentService commentService;
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	public void setImgService(ImgFileService imgService) {
		this.imgService = imgService;
	}
	
	@Autowired
    public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}




	@GetMapping(path="/{id}")
    public void downloadReservationUserCommentImage(
            @PathVariable(name="id") long id,
            HttpServletResponse response
    ){
    	String fileName = imgService.selectOne(id).getSaveFileName();
        String contentType = "image/jpeg";
        //int fileSize = 271621;

        //response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        //response.setHeader("Content-Length", ""+ fileSize);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        java.io.File readFile = new java.io.File(fileName);
        if(!readFile.exists()){ // 파일이 존재하지 않다면
            throw new RuntimeException("file not found");
        }

        FileInputStream fis = null;
        try{
            fis = new FileInputStream(readFile);
            FileCopyUtils.copy(fis, response.getOutputStream()); // 파일을 저장할때도 사용할 수 있다.
            response.getOutputStream().flush();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            try {
                fis.close();
            }catch(Exception ex){
                // 아무것도 하지 않음 (필요한 로그를 남기는 정도의 작업만 함.)
            }
        }
    }
 

    @PostMapping
    public String insertFileImageAndUpdate(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("commentId") int commentId,
            HttpSession session){
    	
    	FileDomain[] fileArray= FileRead.FileReader((Integer)session.getAttribute("id"), files);
        int[] fileId = imgService.insertFileArray(fileArray);
        boolean insertImage = imgService.insertImageArray(commentId, fileId);
        boolean updateFile =  commentService.updateFileName(commentId, fileId[0],fileId.length);
        if(insertImage && updateFile) {
        	  return "redirect:/img";
        }
        // 실패시 main으로
        return "redirect:/";
    }
    
}