package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController 
{
	public String ip() 
	{
						
		String p = null, f = null;
			 
		if (os().contains("Windows")) 
		{
			p = "IPCONFIG " + p;
		} 
		else if (os().contains("Linux"))
		{
			f = "IFCONFIG " + f;
					
		}
				return f;
	}
			
	public void readProcess(String process) 
	{
		try 
		{
			Process p = Runtime.getRuntime().exec(process);
			InputStream leFluxo = p.getInputStream();
			InputStreamReader converteString = new InputStreamReader(leFluxo);
			BufferedReader buffer = new BufferedReader(converteString);
			String linha = buffer.readLine();
			while (linha != null) 
			{
				System.out.println(linha);
				linha = buffer.readLine();
			}
				buffer.close();
				converteString.close();
				leFluxo.close();

		 } 
		catch (Exception e)
		{
			System.err.println("Chamada inválida");
		}
	}
		
	public void readTraceRoute(String proc) 
	{
		String pingWin = null, pingLinux = null;
				
		if (os().contains("Windows"))
		{
			pingWin = "PING " + pingWin;
			
		} 
		else if (os().contains("Linux"))
		{
			pingLinux = "PING" + pingLinux;
					
		}
		try
		{
			Process p = Runtime.getRuntime().exec(proc);
			InputStream fluxo = p.getInputStream();
			InputStreamReader convString = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(convString);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			linha = buffer.readLine();
			linha = buffer.readLine();
			linha = buffer.readLine();
			while (linha != null) 
			{
				String[] vetLinha = linha.split("ms");
				int tamanho = vetLinha.length;
				String ip = vetLinha[tamanho - 1];
						
				if (ip.contains("["))
				{
					String[] vetIp = ip.split("\\[");
					String newIp = vetIp[1].replace("]", "");
					System.out.println(newIp);
				}
				
				else 
				{
					if (!ip.contains("tempo limite")) 
					{
						System.out.println(ip.trim());
					}
				}
						
						linha = buffer.readLine();
						
			}
		  } 
		catch (IOException e) 
		{
			System.err.println("Chamada inválida");
		}
	}
	private String os()
	{
		String OS = System.getProperty("os.name");
		
		return OS;
	}
	
	

}
