package com.scatteredRain.jrpgFlow.general.aiMovement;

import static com.scatteredRain.jrpgFlow.Constants.*;

public abstract class AIMovement {
	
	/** Returns The Desired Direction */
	public abstract int desiredDirection();
	
	/** Updates The AI after a successful movement completion */
	public abstract void moveCompletionUpdate(int x, int y);
	
}
