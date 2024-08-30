package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class RedesController {
	private String os() {
		String name = System.getProperty("os.name");
		System.out.println("Sistema operacional: " + name);
		return name;
	}
	
	public void ip() {
	    String so = os();
	    try {
	    	String linha;
	        Process processo;
	        if (so.contains("Windows")) {
	            processo = Runtime.getRuntime().exec("IPCONFIG");
	            BufferedReader ler = new BufferedReader(new InputStreamReader(processo.getInputStream()));
	        	while ((linha = ler.readLine()) != null) {
	        		if(linha.contains("IPv4")) System.out.println(linha.trim());
		        }
	        	ler.close();
	        } else if (so.contains("Linux")) {
                processo = Runtime.getRuntime().exec("ifconfig");
                BufferedReader ler = new BufferedReader(new InputStreamReader(processo.getInputStream()));
                boolean printNext = false;
                while ((linha = ler.readLine()) != null) {
                    if (linha.contains("flags")) {
                        // This line often contains the name of the adapter
                        String[] splitLine = linha.split(":");
                        System.out.println("Adaptador: " + splitLine[0].trim());
                        printNext = true;
                    } else if (printNext && linha.contains("inet ")) {
                        String[] splitLine = linha.trim().split(" ");
                        System.out.println("IPv4: " + splitLine[1].trim());
                        printNext = false; // Reset for the next adapter
                    }
                }
	        	ler.close();
	        } else {
	            throw new Exception("Sistema operacional não suportado.");
	        }        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}

	public void ping() {
		String so = os();
		Process processo;
		try {
			if (so.contains("Windows")) {
				processo = Runtime.getRuntime().exec("ping -n 4 www.google.com.br");
			} else if (so.contains("Linux")) {
				processo = Runtime.getRuntime().exec("ping -c 4 www.google.com.br");
			} else {
				throw new Exception("Sistema operacional não suportado.");
			}
			InputStream lerDados = processo.getInputStream();
			BufferedReader ler = new BufferedReader(new InputStreamReader(lerDados));
			String linha;
			while ((linha = ler.readLine()) != null) {
	            System.out.println(linha);
	        }
	        ler.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
