package com.redhat.rbost.echofiles;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class EchoFile
 */
@WebServlet("/take")
public class TakeFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      
	      try {
		      ServletFileUpload upload = new ServletFileUpload();

			FileItemIterator iter = upload.getItemIterator(request);
			
		      while (iter.hasNext())
		      {
		        FileItemStream item = iter.next();
		        String name = item.getFieldName();
		        InputStream stream = item.openStream();

       		          byte[] buffer = new byte['x'];
		          int count = 0;
		          int n = 0;
		          while (-1 != (n = stream.read(buffer))) {
		        	  count += n;
		          }

		          String filename = item.getName();
		          
		          response.getWriter().write("Received " + filename);
		          
		          stream.close();
		          response.flushBuffer();
		      }
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
