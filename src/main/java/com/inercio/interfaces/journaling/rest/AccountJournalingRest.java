package com.inercio.interfaces.journaling.rest;

import com.inercio.application.AccountJournalingService;
import com.inercio.domain.model.account.AccountNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Date;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class AccountJournalingRest {

    @Inject
    private AccountJournalingService accountJournalingService;

    @POST
    @Path("account")
    public Response createAccount(@QueryParam("accountNumber") BigDecimal accountNumber,
                                  @QueryParam("accountName") String accountName) {

        GenericResponse response;
        try {
            accountJournalingService.createAccount(accountNumber, accountName);
            response = createResponse(200, "SUCCESS", "Account with number " + accountNumber + " was created successfully");
        } catch (Exception e) {
            response = createResponse(500, "ERROR", "An error occured while creating account");
        }
        return Response.ok(response).build();
    }

    @POST
    @Path("account/debit")
    public Response debitAccount(@QueryParam("accountNumber") BigDecimal accountNumber,
                                 @QueryParam("description") String description,
                                 @QueryParam("amount") BigDecimal amount) throws AccountNotFoundException {

        GenericResponse response;
        try {
            accountJournalingService.debitAccount(accountNumber, description, amount);
            response = createResponse(200, "SUCCESS", amount + " was debited successfully from account " + accountNumber);
        } catch (Exception e) {
            response = createResponse(500, "ERROR", "An error occured while processing debit to account " + accountNumber);
        }
        return Response.ok(response).build();
    }

    @POST
    @Path("account/credit")
    public Response creditAccount(@QueryParam("accountNumber") BigDecimal accountNumber,
                                  @QueryParam("description") String description,
                                  @QueryParam("amount") BigDecimal amount) throws AccountNotFoundException {

        GenericResponse response;
        try {
            accountJournalingService.creditAccount(accountNumber, description, amount);
            response = createResponse(200, "SUCCESS", amount + " was credited successfully into account " + accountNumber);
        } catch (Exception e) {
            response = createResponse(500, "ERROR", "An error occured while processing credit to account " + accountNumber);
        }
        return Response.ok(response).build();
    }

    @POST
    @Path("accountingJournal/entry")
    public Response postEntry(@QueryParam("description") String description,
                              @QueryParam("amount") BigDecimal amount,
                              @QueryParam("fromAccountNumber") BigDecimal fromAccountNumber,
                              @QueryParam("toAccountNumber") BigDecimal toAccountNumber) throws AccountNotFoundException {

        GenericResponse response;
        try {
            accountJournalingService.postEntry(description, amount, fromAccountNumber, toAccountNumber);
            response = createResponse(200, "SUCCESS", "Posted sucessfully into the accounting journal");
        } catch (Exception e) {
            response = createResponse(500, "ERROR", "An error occured while posting accounting journal");
        }
        return Response.ok(response).build();
    }

    @GET
    @Path("accountingJournal/entries")
    public Response listAllEntries() {
        try {
            return Response.ok(accountJournalingService.listAllEntries()).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving entries")).build();
        }

    }

    @GET
    @Path("accountingJournal/entry/{entryId}")
    public Response getEntryDetails(@PathParam("entryId") long entryId) {
        try {
            return Response.ok(accountJournalingService.getEntryDetails(entryId)).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving details for entry with id " + entryId)).build();
        }
    }

    @GET
    @Path("accountingJournal/entry/byDescription/{description}")
    public Response findEntryByDescription(@PathParam("description") String description) {
        try {
            return Response.ok(accountJournalingService.findEntryByDescription(description)).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving details for entry with description " + description)).build();
        }
    }

    @GET
    @Path("accountingJournal/entries/creationDate")
    public Response listAllEntriesbyCreationDate(@QueryParam("creationDate") String date) {
        try {
            return Response.ok(accountJournalingService.listAllEntriesbyCreationDate(new Date(date))).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving entries")).build();
        }
    }

    @GET
    @Path("accountingJournal/entries/betweenDates")
    public Response listAllEntriesCreatedBetweenDates(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        try {
            return Response.ok(accountJournalingService.listAllEntriesCreatedBetweenDates(new Date(startDate), new Date(endDate))).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving entries")).build();
        }
    }

    @GET
    @Path("accounts")
    public Response listAllAccounts() {
        try {
            return Response.ok(accountJournalingService.listAllAccounts()).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving accounts")).build();
        }
    }

    @GET
    @Path("accounts/asset")
    public Response listAssetAccounts() {
        try {
            return Response.ok(accountJournalingService.listAssetAccounts()).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving asset accounts")).build();
        }
    }

    @GET
    @Path("accounts/liability")
    public Response listLiabilityAccounts() {
        try {
            return Response.ok(accountJournalingService.listLiabilityAccounts()).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving liability accounts")).build();
        }
    }

    @GET
    @Path("account/{accountNumber}/entries")
    public Response listPostsMadeToAccount(@PathParam("accountNumber") BigDecimal accountNumber) {
        try {
            return Response.ok(accountJournalingService.listPostsMadeToAccount(accountNumber)).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving entries posted to account " + accountNumber)).build();
        }
    }

    @GET
    @Path("account/{accountNumber}/balance")
    public Response getAccoutCurrentBalance(@PathParam("accountNumber") BigDecimal accountNumber) {
        try {
            return Response.ok(new GenericResponse(200, "SUCCESS", "Current balance for account "
                    + accountNumber + " is " + accountJournalingService.getAccoutCurrentBalance(accountNumber))).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving current balance of account " + accountNumber)).build();
        }
    }

    @GET
    @Path("accountingJournal/assetsValue")
    public Response getAssetsValue() {
        try {
            return Response.ok(new GenericResponse(200, "SUCCESS", "Assets value is " + accountJournalingService.getAssetsValue())).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving assets value.")).build();
        }
    }

    @GET
    @Path("accountingJournal/liabilitiesValue")
    public Response getLiabilitiesValue() {
        try {
            return Response.ok(new GenericResponse(200, "SUCCESS", "Liabilities value is " + accountJournalingService.getLiabilitiesValue())).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving liabilities value.")).build();
        }
    }

    @GET
    @Path("accountingJournal/equityValue")
    public Response getEquityValue() {
        try {
            return Response.ok(new GenericResponse(200, "SUCCESS", "Equity value is " + accountJournalingService.getEquityValue())).build();
        } catch (Exception e) {
            return Response.ok(new GenericResponse(500, "ERROR", "An error occured when retrieving equity value.")).build();
        }
    }

    private GenericResponse createResponse(int code, String message, String description) {
        return new GenericResponse(code, message, description);
    }

}
