package com.Game.main;

import java.awt.Component;
import java.awt.Event;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Movment implements MouseListener,MouseMotionListener {
	private int X,Y;
	public Movment(Component[] pns) {
		for(Component panel : pns){
			panel.addMouseListener(this);
		  	panel.addMouseMotionListener(this);
		// TODO Auto-generated constructor stub
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		e.getComponent().setLocation(e.getX()+e.getComponent().getX()-X,(e.getY()+e.getComponent().getY()-Y));
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		X = arg0.getX();
		Y = arg0.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
