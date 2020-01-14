/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fau.COT4930;

/**
 *
 * @author blswa
 */
public class Player {
    private String name;
    private int score;

	/**
        The constructor creates a default Player object.
	*/
    
	public Player()
	{
            name = "";
            score = 0;
	}
	/**
            The constructor creates a Player object with the specified name.
            @param n represents the name of the Player.
	*/
	public Player(String n)
	{
           name = n;
           score = 0;
	}

	/**
            Method to retrieve the name of the player.
            @return a String representing the name of the Player.
	*/
	public String getName()
	{ // return the name
            return name;
	}
        
	/**
            Method to set the Players name.
            @param n represents the name of the Player.
	*/
	public void setName(String n)
	{ // set the PLayers name
            name = n;
	}
        
        /**
            Method to retrieve the name of the player.
            @return an int representing the score of the Player.
	*/
	public int getScore()
	{ // return the name
            return score;
	}
        
	/**
            Method to set the Players name.
            @param y represents the score of the Player.
	*/
	public void setScore(int y)
	{ // set the Players score
            score = y;
	}
        
        /**
            Method to reset the Players score.
	*/
        public void resetScore() {
            score = 0;
        }
 
 
}
