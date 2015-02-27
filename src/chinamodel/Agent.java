package chinamodel;


import sim.engine.*;
import sim.field.continuous.*;
import sim.util.*;
import sim.field.network.*;


public class Agent implements Steppable{

	public int agentID = 0;
	public double age = 0;
	public double education = 0;
	public int male = 0;
	public int originalProvince = 0;
	public int moved = 0;
	public int hukou = 0;
	public double utility = 0;
	
	public void step(SimState state) {
		JobMarket jobmarket = (JobMarket) state;
		Continuous2D yard = jobmarket.yard;
		
		Double2D me = jobmarket.yard.getObjectLocation(this);
		
		MutableDouble2D sumForces = new MutableDouble2D();
		
		utility = education *4 - age;

		if (moved<1 && utility > 0 && jobmarket.random.nextBoolean(0.5)) {
			
			sumForces.addIn(new Double2D((yard.width * 0.5 - me.x) * 0.1,
					(yard.height * 0.5 - me.y) * 0.1));
				
			sumForces.addIn(new Double2D(10 * (jobmarket.random.nextDouble() * 1.0 - 0.5),
					10 * (jobmarket.random.nextDouble() * 1.0 - 0.5)));
		
			sumForces.addIn(me);
		
			jobmarket.yard.setObjectLocation(this, new Double2D(sumForces));
			
			moved = 1;
		}
		
		
		age++;
	}
	

	
	public static double parameter0;
	public static double parameter1;
	public static double parameter2;
	public static double parameter3;
//	public static double parameter4;
	
	public boolean isMove() {
		
		return true;
	}
	
	
	public double wageDifferential() {
		
		System.out.println("wage differntial");
		return 0;
	}
	
	public double socialNetwork() {
		
		System.out.println("social networks at destination");
		return 0;
	}
	
	public double migrationCost(){
		
		System.out.println("migration cost");
		return 0;
	}
	
/*	public double hukouPolicy() {
		
		System.out.println("Hukou policy stringency");
		return 0;
	}
*/	
	public double utilityOfAgent() {
		
		double value = parameter0
				+parameter1*wageDifferential()
				+parameter2*socialNetwork()
				-parameter3*migrationCost()
//				-parameter4*hukouPolicy()
		;
		return value;
	}
}
