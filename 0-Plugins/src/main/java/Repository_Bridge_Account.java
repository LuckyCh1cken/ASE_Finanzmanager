import Account.*;
import aggregates.Aggregate_Account;
import entities.Entitie_Wallet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import repositories.Repository_Account;
import valueobjects.VO_AccountName;
import valueobjects.VO_Password;
import valueobjects.VO_SpendingType;
import valueobjects.VO_Transaction;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Repository_Bridge_Account implements Repository_Account {

    private final String accountsFileName = "Accounts.json";
    private boolean accountFileIsLoaded = false;

    private final Account_Mapper account_mapper = new Account_Mapper();

    private final List<Aggregate_Account> accounts = new ArrayList<>();


    @Override
    public void addAccount(Aggregate_Account newAccount) {
        if(!accountFileIsLoaded){
            throw new RuntimeException("Accounts are not loaded");
        }
        accounts.add(newAccount);
        saveAccountsToFile(accountsFileName);
    }

    @Override
    public Aggregate_Account getAccountById(String id) {
        if(!accountFileIsLoaded){
            throw new RuntimeException("Accounts are not loaded");
        }
        return accounts.stream().filter(account -> account.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean loadAllAccounts() {
        if(!accountFileIsLoaded){
            accountFileIsLoaded = true;
            return loadAccountsFromFile(accountsFileName);
        }
        return true;
    }

    @Override
    public List<Aggregate_Account> getAllAccounts() {
        if(!accountFileIsLoaded){
            throw new RuntimeException("Accounts are not loaded");
        }
        return accounts;
    }

    @Override
    public boolean saveChanges() {
        if(!accountFileIsLoaded){
            throw new RuntimeException("Accounts are not loaded");
        }
        saveAccountsToFile(accountsFileName);
        return true;
    }

    private void saveAccountsToFile(String fileName)  {

        JSONArray accountListJSONArray = new JSONArray();

        accounts.forEach(account -> {
            Account newAccount = account_mapper.apply(account);

            accountListJSONArray.add(newAccount.toJson());
        });

        try {

            FileWriter file = new FileWriter(fileName);
            file.write(accountListJSONArray.toJSONString());
            file.flush();

        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    private boolean loadAccountsFromFile(String fileName)  {

        JSONParser jsonParser = new JSONParser();

        String jsonFileContent = readOrCreateAccountFile(fileName);

        if(!jsonFileContent.isEmpty()) {
            accounts.clear();

            try {
                Object obj = jsonParser.parse(jsonFileContent);
                JSONArray accountList = (JSONArray) obj;

                accountList.forEach(account -> {
                    Aggregate_Account parsedAccount = parseAccountObject( (JSONObject) account);

                    accounts.add(parsedAccount);
                });

            } catch ( ParseException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    private String readOrCreateAccountFile(String fileName) {

        File file = new File(fileName);
        FileReader fileReader;

        int currentCharacter;
        StringBuilder fileContent = new StringBuilder();

        try {
            file.createNewFile();

            fileReader = new FileReader(file);
            while ((currentCharacter = fileReader.read()) != -1) {
                fileContent.append((char) currentCharacter);
            }
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent.toString();
    }

    private Aggregate_Account parseAccountObject(JSONObject account) {


        String name = (String) account.get("name");
        String hashedPassword = (String) account.get("hpw");

        Entitie_Wallet wallet = new Entitie_Wallet();

        JSONArray tempTransactions = (JSONArray) account.get("transactions");
        tempTransactions.forEach( transaction -> {
          wallet.addTransaction(parseTransactionObject((JSONObject) transaction));
        });

        return new Aggregate_Account(new VO_AccountName(name), new VO_Password(hashedPassword, false), wallet);
    }

    private VO_Transaction parseTransactionObject(JSONObject transaction){
        Double value = (double) transaction.get("Value");
        String spendingType = (String) transaction.get("SpendingType");

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            Date date = formatter.parse((String) transaction.get("Date"));
            return new VO_Transaction(value, new VO_SpendingType(spendingType), date);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
