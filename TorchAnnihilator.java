public class ReliantTorchAnnihilator implements Runnable
{
	private boolean running = true;
	
	private boolean smash = false;
	private int lpX;
	private int lpY;
	private int lpZ;

    public void run()
    {
		lpX = (int)(Math.floor(ReliantWrapper.GetPlayerX()));
		lpY = (int)(Math.floor(ReliantWrapper.GetPlayerY()));
		lpZ = (int)(Math.floor(ReliantWrapper.GetPlayerZ()));
	
		while(running)
		{
			if(smash == false)
			{
				this.Smash();
				smash = true;
			} else {
				int posX = (int)(Math.floor(ReliantWrapper.GetPlayerX()));
				int posY = (int)(Math.floor(ReliantWrapper.GetPlayerY()));
				int posZ = (int)(Math.floor(ReliantWrapper.GetPlayerZ()));
			
				if (ReliantMath.Distance(posX, posY, posZ, lpX, lpY, lpZ) >= 4)
				{
					this.Smash();
					smash = true;
				}
			}
			
			try
			{
				Thread.sleep(150);
			}
			catch (java.lang.InterruptedException ex)
			{
				throw new RuntimeException(ex);
			}
		}
	}
	
	public void Stop()
	{
		running = false;
	}
	
	public void Smash()
	{
		int posX = (int)(Math.floor(ReliantWrapper.GetPlayerX()));
		int posY = (int)(Math.floor(ReliantWrapper.GetPlayerY()));
		int posZ = (int)(Math.floor(ReliantWrapper.GetPlayerZ()));
		int maxX = posX + GetBlockReachDistance();
		int maxY = posY + GetBlockReachDistance();
		int maxZ = posZ + GetBlockReachDistance();
		int minX = posX - GetBlockReachDistance();
		int minY = posY - GetBlockReachDistance();
		int minZ = posZ - GetBlockReachDistance();
		
		for(int i = minX; i < maxX; i++)
		{
			for(int j = minY; j < maxY; j++)
			{
				for(int k = minZ; k < maxZ; k++)
				{
					if(ReliantMath.Distance(posX, posY, posZ, i, j, k) <= GetBlockReachDistance())
					{
						// mcref.world.getBlockId
						int blockid = ReliantWrapper.mcref.e.a(i, j, k);
						qk block = blockid <= 0 ? null : qk.m[blockid];
						
						if(block != null && block == qk.aq)
						{
							ReliantWrapper.GetNetClientHandler().a(((hz) (new hp(0, i, j, k, 0))));
							
							try
							{
								Thread.sleep(35);
							}
							catch (java.lang.InterruptedException ex)
							{
								throw new RuntimeException(ex);
							}
						}
					}
				}
			}
		}
	}
	
	public static int GetBlockReachDistance()
	{
		return 6;
	}
}
