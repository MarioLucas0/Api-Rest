package br.com.residencia.biblioteca.service;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ClienteHttpService {
	/*
	public ResponseEntity<String> sendFoto(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) {
		
		RequestBody fileBody = RequestBody.create(okhttp3.MediaType.parse(file.getContentType()), file.getBytes());
		RequestBody fileBody = file.asRequestBody();
		

        MultipartBody multipartBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)  // Header to show we are sending a Multipart Form Data
            .addFormDataPart("resumeFile", file.getOriginalFilename(),fileBody) // file param
            .addFormDataPart("someText", "textValue") // other string params can be like userId, name or something
            .build();
        
        return null;
	}
	
	public ResponseEntity<String> saveEditoraComFotoOld(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) {
		
		MediaType mediaType
	      = MediaType.parse("text/x-markdown; charset=utf-8");

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(mediaType, file))
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

			System.out.println(response.body().string());
		}
		
	}
	
	public void sendFile(MultipartFile file) {
		RequestBody requestBody = new MultipartBody.Builder()
			      .setType(MultipartBody.FORM)
			      .addFormDataPart("file", "file.txt",
			        RequestBody.create(MediaType.parse("application/octet-stream"), 
			          new File("src/test/resources/test.txt")))
			      .build();

			    Request request = new Request.Builder()
			      .url(BASE_URL + "/users/upload")
			      .post(requestBody)
			      .build();

			    Call call = client.newCall(request);
			    Response response = call.execute();

	}
	*/
}
