package com.xianx.demo;

import java.io.File;
import java.io.StringReader;

import com.oracle.javafx.jmx.json.JSONDocument;
import com.oracle.javafx.jmx.json.JSONFactory;
import com.oracle.javafx.jmx.json.JSONReader;

/**
 * @author xx
 * @date 2017��12��18��
 * 
 */
public class demo {
	public static void main(String[] args) {
		//�����ļ�
		File audioFile = new File("C:\\Users\\dqq\\Desktop\\public\\8k.wav");
		//��������·��
		String BASE_URL = "https://speech.platform.bing.com/speech/recognition/%s/cognitiveservices/v1?language=%s&format=%s";
		/**
		 * ����ʶ��ģʽ  
		 * 1.INTERACTIVE:����ģʽ 
		 * 2.CONVERSATION:�Ựģʽ 
		 * 3.DICTATION:��дģʽ
		 */
		String RecognitionMode = "INTERACTIVE";
		//����ת������
		String language = "zh-CN";
		//1.SIMPLE:�� 2.DETAILED:��ϸ
		String OutputFormat = "SIMPLE";
		//�ܳ�
		String SubscriptionKey = "3b0bf4abb86c4621a54c79ae82d5df5e";
		
		String url = String.format(BASE_URL, RecognitionMode, language, OutputFormat);
		String result = HttpConnector.initialize()
				.url(url)
	            .addHeader(Header.OCP_APIM_SUBSCRIPTION_KEY, SubscriptionKey)
	            .addHeader(Header.ACCEPT, "application/json")
	            .addHeader(Header.TRANSFER_ENCODING, "chunked")
	            .addHeader(Header.CONTENT_TYPE, "audio/wav", "codec=audio/pcm", "samplerate=16000")
	            .post(audioFile);
		
		JSONFactory instance = JSONFactory.instance();
	    JSONReader jsonReader = instance.makeReader(new StringReader(result));
	    JSONDocument jsonDocument = jsonReader.build();
	    System.out.println("�ı���"+jsonDocument.getString("DisplayText"));
	}
	static final class Header {
		static final String OCP_APIM_SUBSCRIPTION_KEY = "Ocp-Apim-Subscription-Key";//key
		static final String CONTENT_TYPE = "Content-Type";//��������
		static final String TRANSFER_ENCODING = "Transfer-Encoding";//�������
		static final String ACCEPT = "Accept";//������������
	}

}
