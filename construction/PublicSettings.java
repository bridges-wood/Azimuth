package construction;

import java.io.Serializable;

public class PublicSettings implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -172633784543168299L;
	float volume;
	
	public PublicSettings() {
		this.volume = 5;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}
}
