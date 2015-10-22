package br.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public String passMD5;
	
	public MD5(String pass) {
		MessageDigest mDigest; 
		try { 
			mDigest = MessageDigest.getInstance("MD5"); 
			byte[] valorMD5 = mDigest.digest(pass.getBytes("UTF-8")); 
			StringBuffer sb = new StringBuffer();
			
			for (byte b : valorMD5){ 
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
			}
			System.out.println(pass +"  "+sb.toString());
			passMD5 = sb.toString(); 
		} catch (NoSuchAlgorithmException e) { 
			e.printStackTrace(); 
			passMD5 = null; 
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace(); 
			passMD5 = null; 
		}  
	}

}
