import java.sql.ResultSet;

public class Menu {

private static final int NUM_OF_FIND_QUERIES = 7; 
private static final int NUM_OF_UPDATE_QUERIES = 3;
private static final int NUM_OF_MENU_OPTIONS = 2;
QueryManager qm;

private void processFind(UI ui) {
String infoDialog = "You have selected FIND. \n"
		+"(0) List all the active prescriptions of a customer \n"
		+"(1) List the details of all the distributors and manufacturers for a compound \n"
		+"(2) List the details of a compound \n"
		+"(3) List the total number of active prescriptions and their detailed information \n"
		+"(4) List the number of refills left and the expiration date of a prescription \n"
		+"(5) List all the prescriptions that have compounds not appropriate for the conditions being treated \n"
		+"(6) List all generic substitutions for a compound \n"
		+"(-1) to return.";
ResultSet rset;
while (true) {
int c = ui.getCommand(NUM_OF_FIND_QUERIES, infoDialog);
switch(c) {
case 0:
	/* List all the active prescriptions of a customer */
	String taxNo = ui.getInfo("What is the taxNo of the customer? ");
	rset = qm.executeQuery("begin open ? for SELECT * " 
					     	 + " FROM Prescription"
						 	+ " WHERE taxNo= " + "'" + taxNo + "'"
						 	+ " AND SYSDATE-dateFiled <= 365; end;");	
	try {
		while (rset.next()) {
			for (int i=1; i<=10; i++) {
				String rset1 = rset.getString(i);
				System.out.println("Prescription info: " + rset1);
			} 
	} 
	} catch (Exception e) {
			e.printStackTrace();
		}
		

	break;
case 1: 
	/* List the details of all the distributors and manufacturers for a compound */
	String brandName = ui.getInfo("What is the brandName? ");
	rset = qm.executeQuery("begin open ? for SELECT Manufacturer.*, Distributor.* " 
						+" FROM Manufacturer LEFT JOIN Seller ON Manufacturer.brandName = Seller.brandName"
						+" LEFT JOIN Distributor ON Distributor.distributorNo = Seller.DistributorNo "
						+" WHERE Seller.brandName = " + "'" + brandName + "'" +"; "
						+ " end;");
	break;
case 2:
	/* List the details of a compound */
	String brandName1 = ui.getInfo("What is the brandName? ");
	rset = qm.executeQuery("begin open ? for SELECT *"
		+ " FROM Compound "
		+" WHERE brandName= " + "'" + brandName1 + "'"
		+ "; end;");

	break;
	
case 3:
	/* List the total number of active prescriptions and their detailed information */
	ResultSet rset3 = qm.executeQuery("begin open ? for SELECT COUNT(*) AS number_of_active_prescriptions "
							+" FROM Prescription WHERE SYSDATE-dateFiled <=365; end;");
	ui.printResults(rset3);
	rset = qm.executeQuery("begin open ? for SELECT * FROM Prescription WHERE SYSDATE-dateFiled <=365;  end;");
	break;
case 4:
	/*List the number of refills left and the expiration date of a prescription */
	String prescriptionNo = ui.getInfo("What is the prescriptionNo? ");
	rset = qm.executeQuery("begin open ? for SELECT Prescription.prescriptionNo, Prescription.dateFiled+365 AS EXP, Refill.refillNum"
	+" FROM Prescription LEFT JOIN Refill ON Prescription.prescriptionNo = Refill.prescriptionNo"
	+" WHERE Prescription.prescriptionNo = " + "'" +prescriptionNo+ "'" +";  end;");
	break;
case 5: 
	/* List all the prescriptions that have compounds not appropriate for the conditions being treated */
	rset = qm.executeQuery("begin open ? for SELECT p.prescriptionNo, p.diagnosticID AS prescriptionDiagnosticID, c.brandName as compoundBrandName,"
		+" c.diagnosticID as compoundDiagnosticID"
		+" FROM Prescription p"
		+" LEFT JOIN Compound c ON p.brandName=c.brandName "
		+" WHERE p.diagnosticID != c.diagnosticID; end;");
	break;
case 6:
	/* List all generic substitutions for a compound */
	String brandNameToSubstitute = ui.getInfo("What is the brandName of the compound you're trying to find generic substitutions for? ");
	rset = qm.executeQuery("begin open ? for SELECT * FROM Compound "
		+" WHERE genericSubstitute = 'Y' "
		+" AND primaryIngredient = ( select primaryIngredient from Compound where brandName ='"+brandNameToSubstitute+"'); end;");
	break;

default:
	return;

} 
if (rset != null)
	ui.printResults(rset);

}

}


private void processUpdate(UI ui) {
		
		String infoDialog = "You have selected UPDATE/DELETE/INSERT. \n"
				+"(0) Update the address information of a physician \n"
				+"(1) Delete all prescriptions that are more than five years old \n"
				+"(2) Insert a new prescription for a new customer \n"
				+ "Select (-1) to return.";
		
		int rowsAffected = 0;
		ResultSet rset;
		while (true) {
			int c = ui.getCommand(NUM_OF_UPDATE_QUERIES, infoDialog);
			switch (c) {
			case 0:
				/* Update the address info for a physician */
				String licenseNo = ui.getInfo("What is the physician's licenseNo? ");
				String address = ui.getInfo("What is the new address? ");
				qm.executeUpdate("begin UPDATE Staff" 
							     + " SET address = " + "'" + address + "'" 
							     + " WHERE licenseNo = " + "'" + licenseNo + "'" + "; end;");
				rset = qm.executeQuery("begin open ? for select * from Staff; end;");
				ui.printResults(rset);
				break;
			case 1:
				/* delete all prescriptions more than 5 years old */
				rset = qm.executeQuery("begin open ? for select * from Prescription where sysdate-dateFiled>1825; end;");
				try {
				while (rset.next()) {
					for (int i=1; i<=10; i++) {
						String rset2 = rset.getString(i);
						System.out.println("Prescription to be deleted: " + rset2);
					} 
				} 
				} catch (Exception e) {
					e.printStackTrace();
				}
				qm.executeUpdate("begin DELETE FROM Prescription where sysdate-dateFiled>1825; end;");

				break;
			case 2:
				/*Insert a new prescription for a new customer*/
				String customerCSV = ui.getInfo("Input customer information" 
					+" in this format: 'taxNo','name','address','DOB','telephone','licenseNo'");
				qm.executeUpdate("begin INSERT INTO Customer VALUES ("+customerCSV+"); end;");
				String prescriptionCSV = ui.getInfo("Input prescription info"
					+" in this format: 'prescriptionNo','dateFiled','quantity','instructions','routeofAdministration',"
					+" 'genericOrWritten(Y for generic N for written)','brandName','diagnosticID','licenseNo','taxNo'");
				qm.executeUpdate("begin INSERT INTO Prescription VALUES ("+prescriptionCSV+"); end;");
				rset = qm.executeQuery("begin open ? for select * from Customer; end;");
				ui.printResults(rset);
			default: 
				return; 
			}	
		}	
	}


public void processQueryCommands(UI ui) {
qm = new QueryManager();
String infoDialog = "Welcome to Prescription Refill DB. Select an option to begin. \n"
	+"(0) FIND - search information \n"
	+"(1) UPDATE/DELETE/INSERT - modify information \n"
	+"(-1) to exit.";
while (true) {
			int c = ui.getCommand(NUM_OF_MENU_OPTIONS, infoDialog);
			switch (c) {
			case 0:
				processFind(ui);
				break;
			case 1:
				processUpdate(ui);
				break;
			default:	
				qm.closeConnection();
				return;

}
}
}

}
