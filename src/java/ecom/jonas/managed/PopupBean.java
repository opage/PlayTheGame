package ecom.jonas.managed;



public class PopupBean  {
	 private boolean visibleEdit = false;
	    private boolean visibleAdd = false;
	    public boolean isVisibleEdit() { return visibleEdit; }
	    
	    public void setVisibleEdit(boolean visible) { this.visibleEdit = visible; }
	    
	    public String closePopupEdit() {
	    	visibleEdit = false;
	    	return null;
	    }
	    
	    public String openPopupEdit() {
	    	visibleEdit = true;
	    	return null;
	    }
	public boolean isVisibleAdd() { return visibleAdd; }
	    
	    public void setVisibleAdd(boolean visible) { this.visibleAdd = visible; }
	    
	    public String  closePopupAdd() {
	    	visibleAdd = false;
	    	return null;
	    }
	    
	    public String openPopupAdd() {
	    	visibleAdd = true;
	    	return null;
	    }
}

		