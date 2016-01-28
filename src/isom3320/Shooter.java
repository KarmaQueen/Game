package isom3320;

/**
 * This was here originally when the game was still adding features.
 * There were originally supposed to be enemies who would shoot the player back,
 * which required the use of this class, but it was no longer required.
 * However, because the game has already been completed, it would be more trouble
 * to get rid of this class than to just leave it in. Which is why this class
 * is still here.
 * @author David
 *
 */
public interface Shooter {
	
	public void cantShootFor(long time);
	
	public Shooter dontCheck();
}
