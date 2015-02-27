package chinamodel;

import sim.engine.*;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;
import sim.util.MutableDouble2D;

public class Province implements Steppable{
	
	public void step(SimState state) {

	}
	
	public int ProvinceID;
	public double capitalInput;
	public double factorProductivity;
	public double alpha;
	public double beta;
	
	public int srr1995;
	public int srr2000;
		
	public int laborDemand() {
		
		return 0;
	}
	
	public double wage() {
		
		return 0;
	}
	
	public double valueOfProvince() {
		
		System.out.println("value of Province");
		return 0;
	}
	
	public double untilityOfProvince() {
		
		System.out.println("utility of Province");
		return 0;
	}
}
