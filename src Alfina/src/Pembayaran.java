public class Pembayaran extends Transaction{
    private double amount; // amount to transfer
    private Keypad keypad; // reference to keypad
    private CashDispenser cashDispenser; // reference to cash dispenser
    private final static int CANCELED = 7; // constant for cancel option

    public Pembayaran(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }

    @Override
    public void execute() {
       amount = displayMenuPembayaran();
       if (amount == CANCELED){
           screen.displayMessageLine("Canceling transaction...\n");
       } else{
           if(cashDispenser.isSufficientCashAvailable((int) amount) && amount != CANCELED){
                if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){    
                    cashDispenser.dispenseCash((int) amount);
                    super.getBankDatabase().debit(super.getAccountNumber(), amount);
//                    super.getBankDatabase().credit(0001, amount);
                    System.out.println("\nYour cash has been dispensed. Please take your cash now.");
//                    super.getBankDatabase().credit(0001, amount);
                }else
                    System.out.println("\nBalance not enough.");
            }else if(!cashDispenser.isSufficientCashAvailable((int) amount))
                System.out.println("\nCash not Available in cash dispenser.");
            else
                System.out.println("\nCanceling transaction...");
            
        } 
    }
    
    private int tiketKereta(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0001);
            screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
             double input = keypad.getInput(); // receive input of deposit amount
             
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nAnda berhasil melakukan pembayaran tiket kereta.\n"
                     + "NOTE: Tiket yang sudah dibayarkan tidak dapat ditukarkan.");

                return (int) (input / 100); // return dollar amount
             }
}
    
    private int OVO(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0002);
            screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
             double input = keypad.getInput(); // receive input of deposit amount
             
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nSaldo OVO anda sudah ditambahkan.\n"
                     + "NOTE: Uang yang sudah dibayarkan tidak dapat dikembalikan.");

                return (int) (input / 100); // return dollar amount
             }
}
    
    private int listrik(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0003);
            screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
             double input = keypad.getInput(); // receive input of deposit amount
             
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nAnda berhasil melakukan pembayaran listrk.\n");

                return (int) (input / 100); // return dollar amount
             }
}
    
    private int paketInternet(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0004);
            screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
             double input = keypad.getInput(); // receive input of deposit amount
             
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nAnda berhasil melakukan pembelian paket internet.\n");

                return (int) (input / 100); // return dollar amount
             }
}
    
    private int asuransi(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0004);
            screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
             double input = keypad.getInput(); // receive input of deposit amount
             
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nAnda berhasil melakukan pembayaran asuransi.\n");

                return (int) (input / 100); // return dollar amount
             }
}
    private int hotel(){
        Screen screen = getScreen(); // get reference to screen
        
//        accountNumbert = 0001; // input account number
        
        Account akun = super.getBankDatabase().getAccount(0004);
            screen.displayMessage("\nMasukan jumlah yang akan dibayarkan " + 
                "CENTS (or 0 to cancel): ");
             double input = keypad.getInput(); // receive input of deposit amount
             
             // check whether the user canceled or entered a valid amount
             if (input == CANCELED) {
                return CANCELED;
             }
             else {
                screen.displayMessage("\nAnda berhasil melakukan pembayaran hotel.\n");

                return (int) (input / 100); // return dollar amount
             }
}
    
      private int displayMenuPembayaran() {
      double userChoice = 0;
      
      boolean userExited = false; // local variable to store return value

      Screen screen = getScreen(); // get screen reference

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
         screen.displayMessageLine("\nMenu Pembayaran :");
         screen.displayMessageLine("1 - Tiket Kereta");
         screen.displayMessageLine("2 - OVO");
         screen.displayMessageLine("3 - Listrik");
         screen.displayMessageLine("4 - Paket Internet");
         screen.displayMessageLine("5 - Asuransi");
         screen.displayMessageLine("6 - Hotel");
         screen.displayMessageLine("7 - Cancel transaction");
         screen.displayMessage("\nPilihan Pembayaran :");

         int input = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         switch (input) {
            case 1: userChoice = tiketKereta(); break;
            case 2: userChoice = OVO(); break;
            case 3: userChoice = listrik(); break;
            case 4: userChoice = paketInternet(); break;
            case 5: userChoice = asuransi(); break;
            case 6: userChoice = hotel(); break;
            case CANCELED:
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
      }// return withdrawal amount or CANCELED
      return (int) userChoice;
   }
} 
