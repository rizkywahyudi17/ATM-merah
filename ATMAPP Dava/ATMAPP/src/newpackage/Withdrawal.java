package newpackage;

/**
 *
 * @author Dava
 */
public class Withdrawal extends Transaction {
   private int amount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 6;

   // Withdrawal constructor
   public Withdrawal(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
      cashDispenser = atmCashDispenser;
   }

   // perform transaction
   @Override
   public void execute() {
       amount = displayMenuOfAmounts();
       
       if (amount == CANCELED) {
           screen.displayMessageLine("Canceling transaction...");
       }
       else{
           if (cashDispenser.isSufficientCashAvailable(amount)) {
               if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){
                   cashDispenser.dispenseCash(amount);
                   super.getBankDatabase().debit(super.getAccountNumber(), amount);
               }
           }
           screen.displayMessageLine("Your cash has been dispensed.");
           screen.displayMessageLine("Please take your cash now.");
       }
   } 

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
   private int displayMenuOfAmounts() {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, 20, 40, 60, 100, 200};

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
         screen.displayMessageLine("\nWithdrawal Menu:");
         screen.displayMessageLine("1 - $20");
         screen.displayMessageLine("2 - $40");
         screen.displayMessageLine("3 - $60");
         screen.displayMessageLine("4 - $100");
         screen.displayMessageLine("5 - $200");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose a withdrawal amount: ");

         int input = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         switch (input) {
            case 1:
            {
                userChoice = amounts[input];
                break;
            }// if the user chose a withdrawal amount 
            case 2:
            {
                userChoice = amounts[input];
                break;
            }// (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3:
            {
                userChoice = amounts[input];
                break;
            }// corresponding amount from amounts array
            case 4:
            {
                userChoice = amounts[input];
                break;
            }
            case 5:
               userChoice = amounts[input]; // save user's choice
               break;       
            case CANCELED: // the user chose to cancel
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
      } 

      return userChoice; // return withdrawal amount or CANCELED
   }
} 