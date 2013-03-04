package com.cnooc.lca.model;

	
	public class T_Position{
		public T_Position(int row, String column){
			this.row = row;
			this.column = column;
		}
		
		private int row;		
		private String column;
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public String getColumn() {
			return column;
		}
		public void setColumn(String column) {
			this.column = column;
		}

	}