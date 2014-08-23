package me.Ste3et_C0st.DiceFreezeMinigame.System;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Ste3et_C0st.DiceFreezeMinigame.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import de.slikey.effectlib.util.ParticleEffect;

public class Arena {
	List<Location> signList = new ArrayList<Location>();
	public int id = 0;
	Location arena = null;
	Location corner1 = null;
	Location corner2 = null;
	Location exit = null;
	List<Player> players = new ArrayList<Player>();
	Boolean started = false;
	String name = "arena";
    Integer maxPlayer = 32;
    Integer lobbyTime = 30;
    Integer roundTimer = 180;
    Integer minPlayer = 2;
	Double money = 5000.0;
	ScoreboardManager manager = Bukkit.getScoreboardManager();
	Scoreboard board = manager.getNewScoreboard();
	HashMap<String, Integer> pPoints = new HashMap<String, Integer>();
	List<Player> froozen = new ArrayList<Player>();
	HashMap<Player, Integer> timerTask = new HashMap<Player, Integer>();
	HashMap<Player, AnimatedBallEntityEffect> ABEE = new HashMap<Player, AnimatedBallEntityEffect>();
	Integer freezeTime = 10;
	Integer teams = 0;
	
	Integer teamFrozen01 = 0;
	Integer teamFrozen02 = 0;	
	Integer teamFrozen03 = 0;
	Integer teamFrozen04 = 0;
	
	List<Player> team01 = new ArrayList<Player>();
	List<Player> team02 = new ArrayList<Player>();
	List<Player> team03 = new ArrayList<Player>();
	List<Player> team04 = new ArrayList<Player>();
	
	Location teamp1 = null;
	Location teamp2 = null;
	Location teamp3 = null;
	Location teamp4 = null;
	
	String t1 = "";
	String t2 = "";
	String t3 = "";
	String t4 = "";
	
	String s1 = "";
	String s2 = "";
	String s3 = "";
	String s4 = "";
	
	String n1 = "";
	String n2 = "";
	String n3 = "";
	String n4 = "";
	
	Boolean timer1 = false;
	Integer lobbyID;
    HashMap<Player, Integer> shield = new HashMap<Player, Integer>();
	Location lobby = null;
	Integer lobbyTimer;
	
    public Arena(Location c1,Location c2,Location arenaSpawn,Location exitArena,Integer min,String Arenaname, Integer ArenaID, Location Lobby){
        this.arena = arenaSpawn;
        this.id = ArenaID;
        this.corner1 = c1;
        this.corner2 = c2;
        this.exit = exitArena;
        this.name = Arenaname;
        this.lobby = Lobby;
        this.minPlayer = min;
    }
    
    public int getTeam(Player p){
    	if(this.team01.contains(p)){
    		return 1;
    	}else if(this.team02.contains(p)){
    		return 2;
    	}else if(this.team03.contains(p)){
    		return 3;
    	}else if(this.team04.contains(p)){
    		return 4;
    	}else{
    		return 999;
    	}
    }
    
    public void setScore(int i, String s){
    	if(i == 1){
    		this.s1 = s;
    	}else if(i == 2){
    		this.s2 = s;
    	}else if(i == 3){
    		this.s3 = s;
    	}else if(i == 4){
    		this.s4 = s;
    	}else{
    		return;
    	}
    }
    
    public Location returnLocation(Integer i){
    	if(i == 1){
    		return this.teamp1;
    	}else if(i == 2){
    		return this.teamp2;
    	}else if(i == 3){
    		return this.teamp2;
    	}else if(i == 4){
    		return this.teamp2;
    	}else{
    		return null;
    	}
    }

    public List<Player> returnTeam(Integer I){
    	if(I == 1){
    		return this.team01;
    	}else if(I == 2){
    		return this.team02;
    	}else if(I == 3){
    		return this.team03;
    	}else if(I == 4){
    		return this.team04;
    	}else{
    		return null;
    	}
    }
    
    public void setfrozen(Integer I, Integer F){
    	if(I == 1){
    		this.teamFrozen01 = F;
    	}else if(I == 2){
    		this.teamFrozen02 = F;
    	}else if(I == 3){
    		this.teamFrozen03 = F;
    	}else if(I == 4){
    		this.teamFrozen04 = F;
    	}else{
    		return;
    	}
    }
    
    public HashMap<Integer, Integer> PlayerSize(){
    	HashMap<Integer, Integer> s = new HashMap<Integer, Integer>();
    	s.put(1, this.team01.size());
    	s.put(2, this.team02.size());
    	s.put(3, this.team03.size());
    	s.put(4, this.team04.size());
    	return s;
    }
    
    public String returnString(Integer I){
    	if(I == 1){
    		return this.t1;
    	}else if(I == 2){
    		return this.t2;
    	}else if(I == 3){
    		return this.t3;
    	}else if(I == 4){
    		return this.t4;
    	}else{
    		return "";
    	}
    }
    
    public String returnColor(Integer I){
    	if(I == 1){
    		return this.n1;
    	}else if(I == 2){
    		return this.n2;
    	}else if(I == 3){
    		return this.n3;
    	}else if(I == 4){
    		return this.n4;
    	}else{
    		return null;
    	}
    }
    
    public void addTeam(Player p, Integer team){
    	if(getTeam(p) != 999){
    		return;
    	}
    	if(team == 1){
    		if(!team01.contains(p)){
        		team01.add(p);
    		}
    	}else if(team == 2){
    		if(!team02.contains(p)){
        		team02.add(p);
    		}
    	}else if(team == 3){
    		if(!team03.contains(p)){
        		team03.add(p);
    		}
    	}else if(team == 4){
    		if(!team04.contains(p)){
    			team04.add(p);
    		}
    	}else{
    		return;
    	}
    }
    
    public Integer returnTeamFromColor(String team){
    	if(t1.equalsIgnoreCase(team)){
    		return 1;
    	}else if(t2.equalsIgnoreCase(team)){
    		return 2;
    	}else if(t3.equalsIgnoreCase(team)){
    		return 3;
    	}else if(t4.equalsIgnoreCase(team)){
    		return 4;
    	}else if(team == null){
    		return null;
    	}else{
    		return 0;
    	}
    }
    
	public int getId() {
		return this.id;
	}

	public boolean isStartet() {
		return this.started;
	}
	
	public Integer teamS(){
		return this.teams;
	}
	
	public void teamlength(Integer i){
		this.teams = i;
	}
	
	
	public void setScoreboard(){
		
		String ScoreName = "FR-" + rnd(999999999, 0); 
		Objective objective = this.board.registerNewObjective(ScoreName, "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(main.s);
		
		for(String p : getPoints().keySet()){
			if(returnScore(p) == 0){
				Score score = objective.getScore(p);
				score.setScore(1);
				score.setScore(0);
			}else{
				Score score = objective.getScore(p);
				score.setScore(getPoints().get(p));
			}

		}
		
		if(teamS() > 0){
			if(teamS() == 2){
				
				String s = ChatColor.translateAlternateColorCodes('&', returnColor(1));
				String s2 = ChatColor.translateAlternateColorCodes('&', returnColor(2));
				int b1 = 0;
				int b2 = 0;
				
				int b11 = 0;
				int b22 = 0;
				
				if(returnTeam(1).size() > 0){
					b11 = returnTeam(1).size();
				}
				
				if(returnTeam(2).size() > 0){
					b22 = returnTeam(2).size();
				}
				
				if(getFroozenTeamCount(1) > 0){
					b1 = getFroozenTeamCount(1);
				}
				
				if(getFroozenTeamCount(2) > 0){
					b2 = getFroozenTeamCount(2);
				}
				
				int b111 = b11 - b1;
				int b222 = b22 - b2;

				if(isStartet() == false){
					for(Player pl : this.team01){
						
						for(Player pls : this.team02){
							pl.hidePlayer(pls);
						}
					}
					
					for(Player pl : this.team02){
						
						for(Player pls : this.team01){
							pl.hidePlayer(pls);
						}
					}
					
					this.s1 = s + b111 + "/" + b11 + " (" + this.minPlayer + ")";
					this.s2 = s2 + b222 + "/" + b22 + " (" + this.minPlayer + ")";
				}else{
					for(Player pl : team01){
						for(Player pls : Bukkit.getOnlinePlayers()){
							pl.showPlayer(pls);
						}
					}
					
					for(Player pl : team02){
						for(Player pls : Bukkit.getOnlinePlayers()){
							pl.showPlayer(pls);
						}
					}
					
					this.s1 = s + b111 + "/" + b11;
					this.s2 = s2 + b222 + "/" + b22;
				}

				
				Score sco = objective.getScore("§7--------------");
				sco.setScore(-1);
				
				Score scoress = objective.getScore(this.s1);
				scoress.setScore(-2);
				
				Score scores = objective.getScore(this.s2);
				scores.setScore(-2);
			}else if(teamS() == 4){
				String s = ChatColor.translateAlternateColorCodes('&', returnColor(1));
				String s2 = ChatColor.translateAlternateColorCodes('&', returnColor(2));
				String s3 = ChatColor.translateAlternateColorCodes('&', returnColor(3));
				String s4 = ChatColor.translateAlternateColorCodes('&', returnColor(4));
				
				
				int b1 = 0;
				int b2 = 0;
				int b3 = 0;
				int b4 = 0;
				
				int b11 = 0;
				int b22 = 0;
				int b33 = 0;
				int b44 = 0;
				
				
				if(returnTeam(1).size() > 0){
					b11 = returnTeam(1).size();
				}
				
				if(returnTeam(2).size() > 0){
					b22 = returnTeam(2).size();
				}
				
				if(getFroozenTeamCount(1) > 0){
					b1 = getFroozenTeamCount(1);
				}
				
				if(getFroozenTeamCount(2) > 0){
					b2 = getFroozenTeamCount(2);
				}
				
				//
				if(returnTeam(3).size() > 0){
					b33 = returnTeam(3).size();
				}
				
				if(returnTeam(4).size() > 0){
					b44 = returnTeam(4).size();
				}
				
				if(getFroozenTeamCount(3) > 0){
					b3 = getFroozenTeamCount(3);
				}
				
				if(getFroozenTeamCount(4) > 0){
					b4 = getFroozenTeamCount(4);
				}
				
				int b111 = b11 - b1;
				int b222 = b22 - b2;
				int b333 = b33 - b3;
				int b444 = b44 - b4;
				

				if(isStartet() == false){
					this.s1 = s + b111 + "/" + b11 + " (" + this.minPlayer + ")";
					this.s2 = s2 + b222 + "/" + b22 + " (" + this.minPlayer + ")";
					this.s3 = s3 + b333 + "/" + b33 + " (" + this.minPlayer + ")";
					this.s4 = s4 + b444 + "/" + b44 + " (" + this.minPlayer + ")";
				}else{
					this.s1 = s + b111 + "/" + b11;
					this.s2 = s2 + b222 + "/" + b22;
					this.s3 = s + b333 + "/" + b33;
					this.s4 = s2 + b444 + "/" + b44;
				}

				
				Score sco = objective.getScore("§7--------------");
				sco.setScore(-1);
				
				Score scoress = objective.getScore(this.s1);
				scoress.setScore(-2);
				
				Score scores = objective.getScore(this.s2);
				scores.setScore(-2);
				
				Score scores1 = objective.getScore(this.s3);
				scores1.setScore(-2);
				
				Score scores2 = objective.getScore(this.s4);
				scores2.setScore(-2);
			}
		}else{
			int b1 = 0;
			int b2 = 0;
			if(getPlayers().size() > 0){
				b1 = getPlayers().size();
			}
			
			if(getFroozenPlayers().size() > 0){
				b2 = getFroozenPlayers().size();
			}
			
			int b3 = b1 - b2;
			
			Score sco = objective.getScore("§7--------------");
			sco.setScore(-1);
			
			if(isStartet()){
				Score scores2 = objective.getScore("§c" + b3 + "§7/§c" + b1);
				scores2.setScore(-2);
			}else{
				Score scores2 = objective.getScore("§c" + b3 + "§7/§c" + b1 + " (" + getMin() + ")");
				scores2.setScore(-2);
			}

		}
		
		for(Player p : getPlayers()){
			p.setScoreboard(this.board);
		}
	}
	
	public HashMap<String, Integer> getPoints(){
		return this.pPoints;
	}

	public Integer returnScore(String s){
		return this.pPoints.get(s);
	}
	
    public int rnd(int max, int min)
    {
      return (int)(Math.random() * (max - min) + min);
    }

	public List<Player> getPlayers() {
		return this.players;
	}

	public String getName() {
		return this.name;
	}
	
	public void addString(String s, Integer i){
		this.pPoints.put(s, i);
	}

	public void setMaxPlayers(int maxP) {
		this.maxPlayer = maxP;
	}
	
	public Integer getMaxPlayer(){
		return this.maxPlayer;
	}

	public void setLobbyTime(int timL) {
		this.lobbyTime = timL;
	}

	public void setGameTimer(int timR) {
		this.roundTimer = timR;
		
	}

	public void setMoney(Double mone) {
		this.money = mone;
	}
    
    public void updateSign(Location loc)
    {
        if(!isSignExist(loc)){
        	addSign(loc);
        }
        String s = "";
        
		if(isStartet()){
			s = "§cRunning";
		}else if(timer1 == true){
			s = "§cStarting";
		}else{
			s = "§8" + getPlayers().size() + "/" + getMaxPlayes();
		}
        
        BlockState state = loc.getBlock().getState();
        if(state instanceof Sign == false)
            return;
        org.bukkit.block.Sign sign = (org.bukkit.block.Sign)state;
        sign.setLine(3, s);
        
        sign.update();
    }
    
	public boolean isSignExist(Location s){
		for(Location ls : getSign()){
			if(ls.equals(s)){
				return true;
			}
		}
		return false;
	}
	
	public List<Location> getSign(){
		return this.signList;
	}
	
	public void addSign(Location s){
		this.signList.add(s);
	}

	public Integer getMin() {
		return this.minPlayer;
	}

	public Integer getMaxPlayes() {
		return this.maxPlayer;
	}

	public Integer getRoundTimer() {
		return this.roundTimer;
	}

	public Integer getLobbyTimer() {
		return this.lobbyTime;
	}

	public Double getMoney() {
		return this.money;
	}



	public void addPlayer(Player p) {
		this.players.add(p);
	}



	public boolean isFroozen(Player p) {
		if(!froozen.isEmpty()){
			for(Player pl : froozen){
				if(pl.equals(p)){
					return true;
				}
			}
		}
		return false;
	}

	public void createTeam(Integer i, Location l, String n, String c){
		if(i == 1){
			this.teamp1 = l;
			this.t1 = n;
			this.n1 = c;
		}else if(i == 2){
			this.teamp2 = l;
			this.t2 = n;
			this.n2 = c;
		}else if(i == 3){
			this.teamp3 = l;
			this.t3 = n;
			this.n3 = c;
		}else if(i == 4){
			this.teamp4 = l;
			this.t4 = n;
			this.n4 = c;
		}
	}

	@SuppressWarnings("deprecation")
	public void freeze(Player hit) {
		froozen.add(hit);
		AnimatedBallEntityEffect ab = new AnimatedBallEntityEffect(main.effectManager , (Entity) hit);
		ParticleEffect ap = ParticleEffect.FIREWORKS_SPARK;
		ab.particle = ap;
		ab.size = 1F;
		ab.period = (int) 0.1F;
		ab.start();
		ABEE.put(hit, ab);
		ItemStack ice = new ItemStack(Material.ICE);
		ItemMeta icem = ice.getItemMeta();
		ice.setItemMeta(icem);
		hit.getInventory().clear();
		hit.updateInventory();
		ice.setAmount(64);
		hit.getInventory().addItem(ice);
		hit.getInventory().addItem(ice);
		hit.getInventory().addItem(ice);
		hit.getInventory().addItem(ice);
		hit.getInventory().addItem(ice);
		hit.getInventory().addItem(ice);
		hit.getInventory().addItem(ice);
		hit.getInventory().addItem(ice);
		hit.updateInventory();
		ItemStack IS = new ItemStack(Material.NETHER_STAR);
		ItemMeta IM = IS.getItemMeta();
		IM = IS.getItemMeta();
		IM.setDisplayName(main.s + "Back To the lobby");
		IS.setItemMeta(IM);
		hit.getInventory().addItem(IS);
		playerFreezeTimer(hit);
		hit.updateInventory();
	}

	

	public Location getFroozen(Player p) {
		if(!froozen.isEmpty()){
			for(Player pl : froozen){
				if(pl.equals(p)){
					return pl.getLocation();
				}
			}
		}
		return null;
	}



	public int getString(String string) {
		if(!pPoints.isEmpty()){
			for(String s : pPoints.keySet()){
				if(s.equalsIgnoreCase(string)){
					return pPoints.get(string);
				}
			}
		}
		return 0;
	}
	
	public void removeFroozen(Player p){
		if(isFroozen(p)){
			for(int i = 0; i <= froozen.size(); i++){
				if(froozen.get(i).equals(p)){
					froozen.remove(i);
					break;
				}
			}
		}
	}
	
	public Integer getFreezeTime(){
		return this.freezeTime;
	}
	
	public void playerFreezeTimer(final Player p){
		Plugin pl = me.Ste3et_C0st.DiceFreezeMinigame.main.getInstance();
		timerTask.put(p, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
			int r = getFreezeTime();
			int o = r;
			@Override
			public void run() {
				p.setLevel(o - 1);
				this.o--;
				if (this.o <= 0)
                {
					cancelPLayerFreezeTimer(p);
					unfroozenPlayer(p);
					givePlayer(p);
                }
			}
		}, 0L, 20L));
	}

	public void playerShield(final Player p){
		Plugin pl = me.Ste3et_C0st.DiceFreezeMinigame.main.getInstance();
		shield.put(p, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
			int r = 4;
			int o = r;
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				p.setLevel(o - 1);
				ItemStack IS = new ItemStack(Material.APPLE);
				ItemMeta IM = IS.getItemMeta();
				IM.setDisplayName("Shield:" + p.getLevel());
				IS.setItemMeta(IM);
				IS.setAmount(p.getLevel());
				p.getInventory().setItem(4, IS);
				
				ItemStack is = new ItemStack(Material.NETHER_STAR);
				ItemMeta im = is.getItemMeta();
				im.setDisplayName(main.s + "Back To the lobby");
				is.setItemMeta(im);
				p.getInventory().setItem(7, is);
				
				p.updateInventory();
				this.o--;
				if (this.o <= 0)
                {
					cancelShield(p);
					p.getInventory().setItem(4, null);
					p.updateInventory();
                }
			}
		}, 0L, 20L));
	}
	
	public void cancelShield(Player p){
		if(isShield(p)){
			Bukkit.getScheduler().cancelTask(shield.get(p));
			shield.remove(p);
		}
	}
	
	public boolean isShield(Player p){
		if(shield.get(p) != null){
			return true;
		}else{
			return false;
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void unfroozenPlayer(Player p) {
		if(ABEE.get(p) != null){
			ABEE.get(p).cancel();
			ABEE.remove(p);
		}
		
		if(getTeam(p) != 0){
			
			if(isFroozen(p)){
				int i = getTeam(p);
				int a = getFroozenTeamCount(i) - 1;
				setfrozen(i, a);
			}
		}

		p.getInventory().clear();
        p.updateInventory();
        ItemStack is = new ItemStack(Material.SNOW_BALL);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(main.s + "Snow ball");
        is.setItemMeta(im);
        p.getInventory().addItem(is);
        p.updateInventory();
        removeFroozen(p);
        p.playSound(p.getLocation(), Sound.ANVIL_USE, 1, 1);
        playerShield(p);
        p.setLevel(0);
	}

	@SuppressWarnings("deprecation")
	public void givePlayer(Player p) {
		if(getTeam(p) != 0){
			int nb = getTeam(p);
			String color = returnColor(nb);
            ItemStack IS = new ItemStack(Material.WOOL);
            ItemMeta IM = IS.getItemMeta();
            IM.setDisplayName("Team: " + ChatColor.translateAlternateColorCodes('&', color) + returnString(nb));
            IS.setItemMeta(IM);
            int c = 0;
            
			if(color.equalsIgnoreCase("&a")){
				c = 5;
			}else if(color.equalsIgnoreCase("&b")){
				c = 3;
			}else if(color.equalsIgnoreCase("&c")){
				c = 14;
			}else if(color.equalsIgnoreCase("&d")){
				c = 6;
			}else if(color.equalsIgnoreCase("&e")){
				c = 4;
			}else if(color.equalsIgnoreCase("&f")){
				c = 0;
			}else if(color.equalsIgnoreCase("&0")){
				c = 15;
			}else if(color.equalsIgnoreCase("&1")){
				c = 11;
			}else if(color.equalsIgnoreCase("&2")){
				c = 13;
			}else if(color.equalsIgnoreCase("&3")){
				c = 9;
			}else if(color.equalsIgnoreCase("&4")){
				c = 14;
			}else if(color.equalsIgnoreCase("&5")){
				c = 10;
			}else if(color.equalsIgnoreCase("&6")){
				c = 1;
			}else if(color.equalsIgnoreCase("&7")){
				c = 8;
			}else if(color.equalsIgnoreCase("&8")){
				c = 7;
			}else if(color.equalsIgnoreCase("&9")){
				c = 11;
			}
			
			IS.setDurability((short) c);
			p.getInventory().setItem(8, IS);
			p.updateInventory();
		}
	}

	public boolean isPLayerFreezeTimer(Player p){
		if(timerTask.get(p) != null){
			return true;
		}else{
			return false;
		}
	}
	
	public void cancelPLayerFreezeTimer(Player p){
		if(isPLayerFreezeTimer(p)){
			Bukkit.getScheduler().cancelTask(timerTask.get(p));
			timerTask.remove(p);
		}
	}

	public boolean returnGame(){
		return this.timer1;
	}
	
	public void lobbyTimer() {
	    Plugin pl = me.Ste3et_C0st.DiceFreezeMinigame.main.getInstance();
	    this.lobbyTimer = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
	    	int i = getLobbyTimer();
			@SuppressWarnings("deprecation")
			public void run()
            {
            	
            	for(Player p : getPlayers()){
            		p.setLevel(i - 1);
            	}
            	
                this.i--;
                if (this.i <= 0)
                {

                    	lobbyTimerCancel();
                    	for(Player p : getPlayers()){
                    		p.getInventory().clear();
                    		
                    		if(getTeam(p) == 999){
                    			if(teamS() == 2){
                    				int team1 = returnTeam(1).size();
                    				int team2 = returnTeam(2).size();
                    				if(team1 < team2){
                    					addTeam(p, 1);
                    				}else if(team1 > team2){
                    					addTeam(p, 2);
                    				}else{
                    					addTeam(p, 1);
                    				}
                    			}else{
                    				int team1 = returnTeam(1).size();
                    				int team2 = returnTeam(2).size();
                    				int team3 = returnTeam(3).size();
                    				int team4 = returnTeam(4).size();
                    				if(team1 < team2 && team3 >= team4){
                    					addTeam(p, 1);
                    				}else if(team2 < team1 && team3 >= team4){
                    					addTeam(p, 2);
                    				}else if(team3 < team4 && team2 >= team1){
                    					addTeam(p, 3);
                    				}else if(team4 < team3 && team1 >= team2){
                    					addTeam(p, 4);
                    				}else{
                    					addTeam(p, 4);
                    				}
                    			}
                    		}
                    		
                    		ArenaManager.getManager().addPlayer(p, id, returnString(getTeam(p)));
            				ItemStack IS = new ItemStack(Material.NETHER_STAR);
            				ItemMeta IM = IS.getItemMeta();
            				IM.setDisplayName(main.s + "Back To the lobby");
            				IS.setItemMeta(IM);
            				p.getInventory().setItem(8, IS);
            				p.updateInventory();
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                    	}

                }else if(this.i == 20 || this.i == 10 || this.i <= 5){
                	for(Player p : getPlayers()){
                		p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                	}
                }
            }
	    }, 0L, 20L);
	}
	
	public void lobbyTimerCancel(){
		if(lobbyTimer != null){
			Bukkit.getScheduler().cancelTask(lobbyTimer);
			lobbyTimer = null;
		}
	}
	
	public void startGame() {
	    Plugin pl = me.Ste3et_C0st.DiceFreezeMinigame.main.getInstance();
	    this.timer1 = true;
	    this.lobbyID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){
	    	int i = getLobbyTimer();
			@SuppressWarnings("deprecation")
			public void run()
            {
            	
            	for(Player p : getPlayers()){
            		p.setLevel(i - 1);
            	}
            	
                this.i--;
                if (this.i <= 0)
                {

                    	cancelGame();
                    	for(Player p : getPlayers()){
                    		p.getInventory().clear();
                            ItemStack is = new ItemStack(Material.SNOW_BALL);
                            ItemMeta im = is.getItemMeta();
                            im.setDisplayName(main.s + "Snow ball");
                            is.setItemMeta(im);
                            
            				ItemStack IS = new ItemStack(Material.NETHER_STAR);
            				ItemMeta IM = IS.getItemMeta();
            				IM.setDisplayName(main.s + "Back To the lobby");
            				IS.setItemMeta(IM);
                            
                            p.getInventory().addItem(is);
                            p.getInventory().setItem(7, IS);
                            p.updateInventory();
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                            givePlayer(p);
                            
                    	}

                    	started = true;
                	
                    	setScoreboard();
                        if(!getSign().isEmpty()){
                        	for(Location l : getSign()){
                        			updateSign(l);
                        		}
                        }

                }else if(this.i == 20 || this.i == 10 || this.i <= 5){
                	for(Player p : getPlayers()){
                		p.sendMessage(main.s + Message.replace("Die Arena startet in %SECOUNDS% Sekunden", "%SECOUNDS%", i + ""));
                		p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                	}
                }
            }
	    }, 0L, 20L);
	}
	
	public void cancelGame(){
				Bukkit.getScheduler().cancelTask(this.lobbyID);
	}

	public List<Player> getFroozenPlayers() {
		return froozen;
	}

	public Location getCorner1() {
		return this.corner1;
	}
	
	public Location getCorner2() {
		return this.corner2;
	}

	public Location getArenaSpawn() {
		return this.arena;
	}

	public void removePlayerTeam(int aID, Player p) {
		int i = 0;
		if(aID == 1){
			for(Player pl : this.team01){
				if(pl.equals(p)){
				team01.remove(i);
				break;
			}else{
				i++;
			}
		}
	}else if(aID == 2){
		for(Player pl : this.team02){
			if(pl.equals(p)){
			team02.remove(i);
			break;
		}else{
			i++;
		}
		}
	}else if(aID == 3){
		for(Player pl : this.team03){
			if(pl.equals(p)){
			team03.remove(i);
			break;
		}else{
			i++;
		}
		}
	}else if(aID == 4){
		for(Player pl : this.team04){
			if(pl.equals(p)){
			team04.remove(i);
			break;
		}else{
			i++;
		}
		}
	}
		
	}

	public void removeString(String name2) {
		if(!this.pPoints.isEmpty()){
			if(this.pPoints.get(name2) != null){
				this.pPoints.remove(name2);
			}
		}
	}


	public void removePlayer(Player p) {
		if(!getPlayers().isEmpty()){
			int i = 0;
			for(Player pl : this.players){
					if(pl.equals(p)){
						this.players.remove(i);
						break;
					}else{
						i++;
					}
			}
		}
		
	}

	public void setMin(int parseInt) {
		this.minPlayer = parseInt;
	}

	public int getFroozenTeamCount(int teamHit) {
		if(teamHit == 1){
			return this.teamFrozen01;
		}else if(teamHit == 2){
			return this.teamFrozen02;
		}else if(teamHit == 3){
			return this.teamFrozen03;
		}else if(teamHit == 4){
			return this.teamFrozen04;
		}else{
			return 99999999;
		}
	}

	public void checkTeamSize() {
		if(teamS() == 2){
			int min = minPlayer;
			if(team01.size() == 0 || team02.size() == 0){
				if(lobbyTimer != null){
					lobbyTimerCancel();
				}
			}
			
			if(team01.size() >= min && team02.size() >= min){
				if(lobbyTimer == null){
					lobbyTimer();
				}
			}else{
				if(lobbyTimer != null){
					lobbyTimerCancel();
				}
			}
		}
	}
}
