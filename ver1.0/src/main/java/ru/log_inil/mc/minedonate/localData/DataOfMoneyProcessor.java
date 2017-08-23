
package ru.log_inil.mc.minedonate.localData;

public class DataOfMoneyProcessor {

	public String moneyType ;
	public String className ;
	public String dbTable ;
    public String dbIdColumn;
    public String dbMoneyColumn ;
	public boolean isTwoSideProcessor ;
	public int regMoney ;
	
	public DataOfMoneyProcessor ( ) {
		
		moneyType = "unknown" ;
		className = "?" ;
		regMoney = 0 ;
		
	}

    public DataOfMoneyProcessor(String _mt, String _className, String _dbTable, String _dbIdColumn, String _dbMoneyColumn, boolean _isTwoSideProcessor) {

        moneyType = _mt ;
		className = _className ;
		dbTable = _dbTable ;
        dbIdColumn = _dbIdColumn;
        dbMoneyColumn = _dbMoneyColumn ;
		isTwoSideProcessor = _isTwoSideProcessor; ;
		regMoney = 0 ;
		
	}
	
}
