
	public class RestPlace extends Object{
		public RestPlace(String name, String description) {
			super(false, name, description, null, 0, 0);
		}
		
		public Character rest(Character subject, int durationHrs) {
			int preHP = subject.getHp(); //Finish method to reset hp to max after rest.
			return subject;
		}
	}