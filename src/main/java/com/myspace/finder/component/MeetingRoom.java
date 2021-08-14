package com.myspace.finder.component;

import com.myspace.finder.constant.MySpaceRoomFinderConstant;

public class MeetingRoom {
   private  boolean slot[] = new  boolean[MySpaceRoomFinderConstant.MAX_SLOT_PER_DAY];
   public boolean slotOccupied(int startIndex, int endIndex ) 
   {
	   boolean result = false;
	   for ( int idx = startIndex; idx < endIndex; idx++ ) 
	   {
		   if ( slot[idx]  )
		   {
			   result = true;
			   break;
		   }
			   
	   }
	   return result;
   }
   
   public void insertSlots(int startIndex, int endIndex)
   {
	   for ( int idx = startIndex; idx < endIndex; idx++ )
		   slot[idx] = true;
   }
}
