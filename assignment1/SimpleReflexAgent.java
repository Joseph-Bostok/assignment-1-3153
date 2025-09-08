public class SimpleReflexAgent {

	public static void main(String[] args) 
	{
		Environment environment;
		
		//	Process command-line arguments
		if (args.length < 1)
		{
			System.out.println("Usage: java SimpleReflexAgent start-location roomA-status roomB-status");
			return;
		}
		else
		{
			int agentLocation = Integer.parseInt(args[0]);
			boolean rooms[] = new boolean[2];
			rooms[0] = Boolean.parseBoolean(args[1]);
			rooms[1] = Boolean.parseBoolean(args[2]); 
			
			environment = new Environment(agentLocation, rooms);
		}
		
		System.out.println("Agent Started...");
	

		
		for(int i=0; i<5; i++)
		{
			System.out.println("Environment Status:");
			System.out.println("\tAgent Location: " + environment.getAgentLocation());
			
			for (int locationIndex = 0; locationIndex < 2; locationIndex++)
			{
				System.out.print("\tLocation " + locationIndex + ": ");
				
				if (environment.getRooms()[locationIndex])
					System.out.println("Dirty");
				else
					System.out.println("Clean");
			}
			
			System.out.println();
			
			
			Percept percept = perceiveEnvironment(environment);
			
			AgentAction action = reflexVacuumAgent(percept);
			
			performAction(action, environment);
		}
	}

	
	private static Percept perceiveEnvironment(Environment environment){
        Percept p = new Percept();
		int location = environment.getAgentLocation();
		p.setLocation(location);
		boolean[] rooms = environment.getRooms();
		boolean isDirty = rooms[location];
		p.setisDirty(isDirty);

		return p;

	}
	
	
	private static void performAction(AgentAction action, Environment environment){

		switch (action){
			case Clean: 
				System.out.println("action: cleaing room");
				int agentLocation = environment.getAgentLocation();
				boolean[] rooms = environment.getRooms();
				rooms[agentLocation] = false; //room is clean
				environment.setRooms(rooms);
				break;

			case MoveLeft:
				System.out.println("action: moving left");
				environment.setAgentLocation(0); //room A would be at index 0
				break;

			case MoveRight:
				System.out.println("Action:moving right");
				environment.setAgentLocation(1); //room B would be at index 1
			
				case NoOp:
				System.out.println("action: No operation"); //do nothing
				break;
		}
	}
		
		
	
	
	private static AgentAction reflexVacuumAgent(Percept percept)
	{
		if (percept.isDirty()) {
            return AgentAction.Clean;

        } else if (percept.getLocation() == 0) { // in room A (index 0) and clean
            return AgentAction.MoveRight;

        } else if (percept.getLocation() == 1) { //in room B (index 1) and clean
            return AgentAction.MoveLeft;
        }
		
        return AgentAction.NoOp; 
    }
	}
