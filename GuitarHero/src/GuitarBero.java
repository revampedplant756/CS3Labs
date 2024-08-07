
public class GuitarBero {
	public static void main(String[] args) {
		GuitarString[] guitar = new GuitarString[37];
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		
		for(int e = 0; e < guitar.length; e++) {
			guitar[e] = new GuitarString(440.0 * Math.pow(1.05956, e-24));
		}
		
		while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if(keyboard.indexOf(key) != -1) {
                	guitar[keyboard.indexOf(key)].pluck();
                }
            }
            
            // compute the superposition of the samples
            double sample = 0;
            
            for(int e = 0; e < guitar.length; e++) {
    			sample += guitar[e].sample();
    		}
            
            System.out.println(sample);
            
            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for(int e = 0; e < guitar.length; e++) {
    			guitar[e].tic();
    		}
		}
	}
}
