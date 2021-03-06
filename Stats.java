public class Stats {
	
	/*
	Calculates the expected value of playing to a specific amount with a specified wager and bet.
	Expected value is calculated as the probability of winning times the amount won (plus probabilty of losing
	time zero which is ignored). This method calls from Sims.java for calculating probabilty.
	*/
	public static double expected_value(int[] bet, int wager, double mult, int payout,
	int start) {
		double exp_value;
		double prob = Sims.play_to_mult(bet, wager, mult, payout, start);
		exp_value = prob*start;
		return exp_value;	
	}
	
	/*
	Allows for multiple bets
	*/
	public static double expected_value(int[][] bet, int wager, double mult, int payout[],
	int start) {
		double exp_value;
		double prob = Sims.play_to_mult(bet, wager, mult, payout, start);
		exp_value = prob*start*mult;
		return exp_value;	
	}
	
	/*
	Calculated the standard deviation in amount won while playing to a specific amount with a specified wager and bet.
	Standard deviation is calculated using the definition of variance as the expectation of the win probability squared 
	minus the square of the expectation value.
	*/
	public static double calc_sd(int[] bet, int wager, double mult, int payout,
	int start) {
		double var;
		double exp_value_sqrd;
		double prob = Sims.play_to_mult(bet, wager, mult, payout, start);
		exp_value_sqrd = prob*(start*mult)*(start*mult);
		double exp_value = expected_value(bet, wager, mult, payout, start);
		var = exp_value_sqrd-(exp_value*exp_value);
		return (Math.sqrt(var));
	}
	
	/*
	Allows for multiple bets
	*/
	public static double calc_sd(int[][] bet, int wager, double mult, int[] payout,
	int start) {
		double var;
		double exp_value_sqrd;
		double prob = Sims.play_to_mult(bet, wager, mult, payout, start);
		exp_value_sqrd = (prob*prob)*start;
		double exp_value = expected_value(bet, wager, mult, payout, start);
		var = (exp_value*exp_value)-exp_value_sqrd;
		return (Math.sqrt(var));
	}
	/*
	Integrates using left handed rectangle method. Creates an array of x values spanning from the lower bound to upper bound.
	This array is passed to the normal_pdf function to create an array of y values. The double result has the value of each
	rectangle added to it, also allowing for negative values.
	*/
	
	public static double integrate(double lower, double upper, double inc, double mu, double sigma) {
		double result=0;
		int len_x = (int) ((upper-lower)/inc);
		double[] x = new double[len_x];
		for(int i=0;i<len_x;i++) {
			x[i] = lower + (inc*i);
		}
		double [] func = normal_pdf(x,mu,sigma);
		
		for(int i=0;i<len_x;i++) {
			result += func[i]*inc;
		}
		
		return result;
	}
	/*
	Converts x array into the normal pdf for integration.
	*/
	public static double[] normal_pdf(double[] x, double mu,double sigma) {
		double[] y = new double[x.length];
		for(int i=0;i<y.length;i++) {
			y[i]=(1/(sigma*Math.sqrt(2*Math.PI)))*
					Math.exp(-0.5*(((x[i]-mu)/sigma)*(x[i]-mu)/sigma)) ;
		}
		return y;
		
		
	}
	
	
}
