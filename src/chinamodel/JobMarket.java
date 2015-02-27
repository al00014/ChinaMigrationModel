package chinamodel;


import sim.engine.*;
import sim.util.*;
import sim.field.continuous.*;
import sim.field.network.*;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.util.ArrayList;  

public class JobMarket extends SimState{
	
	
	public Agent agent;
	public Province province;
	
	public boolean isAcceptOffer() {
		
		if (agent.utilityOfAgent() > 0)
		{
			return true;
		}
		else
			return false;
	}
	
	public Continuous2D yard = new Continuous2D(1.0,100,100);
	public int numAgents = 500;
	public int numProvinces = 32;
	
	public Network buddies = new Network(false);
	
	public JobMarket(long seed) {
		super(seed);
	}
	
	public void start() 
	{
		super.start();

		yard.clear();
		

		try { 
			BufferedReader reader = new BufferedReader(new FileReader("C:/Users/fzh/Desktop/China's rural-urban migration/Data/data.csv"));
			reader.readLine();
			String line = null; 
			int i = 0;
	            
			while(i < numAgents && (line=reader.readLine())!=null){ 
				Agent agent = new Agent();
				yard.setObjectLocation(agent,
						new Double2D(yard.getWidth() * 0.3 + random.nextDouble()*50 - 0.5,
							yard.getHeight() * 0.3 + random.nextDouble()*50 - 0.5));
				String item[] = line.split(",");               
		
				agent.originalProvince = Integer.parseInt(item[0]);
				agent.age = Integer.parseInt(item[1]);
				agent.education = Integer.parseInt(item[2]);
				agent.male = Integer.parseInt(item[3]);
				agent.moved = Integer.parseInt(item[4]);
				agent.hukou = Integer.parseInt(item[5]);
				agent.agentID = i;
	              
				//System.out.println(Integer.parseInt(item[1]));//test
	            
				schedule.scheduleRepeating(agent);
				
				i++;
			} 
		}	
		catch (Exception e) { 
	        	e.printStackTrace(); 
		}
	        
		
		try { 
			BufferedReader reader = new BufferedReader(new FileReader("C:/Users/fzh/Desktop/China's rural-urban migration/Data/provinceData.csv"));
			reader.readLine();
			String line = null; 
			int i = 0;
	            
			while(i < numProvinces && (line=reader.readLine())!=null){ 
				Province province = new Province();
				
				yard.setObjectLocation(province,
					new Double2D(yard.getWidth() * 0.3 + random.nextDouble()*30 - 0.5,
						yard.getHeight() * 0.3 + random.nextDouble()*30 - 0.5));
			
		
				String item[] = line.split(",");
	                 
				province.ProvinceID = Integer.parseInt(item[0]);
				province.srr1995 = Integer.parseInt(item[1]);
				province.srr2000 = Integer.parseInt(item[2]);
	
	             
				//System.out.println(Integer.parseInt(item[0]));//test
	            
				schedule.scheduleRepeating(province);
				
				i++;
			} 
		}	
		catch (Exception e) { 
	        	e.printStackTrace(); 
		}
		
			
		// define like/dislike relationships

	}
	
	public static void main(String[] args)
	{
		doLoop(JobMarket.class, args);
		System.exit(0);
	}


}
