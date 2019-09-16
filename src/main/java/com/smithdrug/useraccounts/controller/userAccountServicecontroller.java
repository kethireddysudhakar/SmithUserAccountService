package com.smithdrug.useraccounts.controller;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.smithdrug.response.WebServiceResponse;
import com.smithdrug.response.accounts.ResponseData;
import com.smithdrug.response.accounts.SmithResponseData;
import com.smithdrug.response.accounts.UserInformationData;
import com.smithdrug.response.codes.ResponseCodes;
import com.smithdrug.response.error.ErrorData;
import com.smithdrug.response.error.ErrorResponseData;
import com.smithdrug.response.status.SmithStatus;
import com.smithdrug.response.status.SmithStatus.status;
import com.smithdrug.useraccounts.entity.UserInformation;
import com.smithdrug.useraccounts.repository.userAccountRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/userAccountServices")
@Api(value="User account Store", description="Operations pertaining to User account Services")
@Validated
@RefreshScope
public class userAccountServicecontroller {
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired 
	private userAccountRepository repository;
	
	private SmithStatus smithStatus = null;
	private ResponseData data =null;
	private ErrorResponseData error =null;
	private WebServiceResponse response = null;
	private String org="egate";

	@ApiOperation(value = "Quick check if the service is up and running",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully tested the service availability"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping(path = "/serviceCheck")
	public ResponseEntity<?> quickServiceChecker() {
		SmithStatus smithStatus = new SmithStatus(status.SUCCESS);
		SmithResponseData data = new SmithResponseData("Service up and running");
		ErrorResponseData error = new ErrorResponseData(null);
		WebServiceResponse response = new WebServiceResponse(smithStatus, data, error);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get information about user",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "Data validation failed"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping(path = "/v1/getUserInfo/{accountNumber}")
	public ResponseEntity<?> getUserInfo(@PathVariable @NotNull String accountNumber)
	{
		UserInformation userInfo = null;
		try {
			userInfo = repository.getUserInfo(accountNumber);
		}
		catch(Exception ex)
		{
			userInfo = null;
		}
		if(null != userInfo)
		 {
				smithStatus = new SmithStatus(status.SUCCESS);
				data = new UserInformationData(userInfo.getEmailAddress(), userInfo.getLocation(), userInfo.getAccountNumber(), userInfo.getCustName(),
						userInfo.getAddress1(), userInfo.getAddress2(), userInfo.getAddress3(), userInfo.getState(), userInfo.getCity(), userInfo.getZip(), userInfo.getCountry(),
						userInfo.getPhone());
				error = null;
				response = new WebServiceResponse(smithStatus, data, error);
				return new ResponseEntity<>(response, HttpStatus.OK);
		 }
		 else
		 {
		 	smithStatus = new SmithStatus(status.FAILURE);
			ErrorData errorMessages = new ErrorData(ResponseCodes.failure, ResponseCodes.userNotFound);
			error = new ErrorResponseData<ErrorData>(Arrays.asList(errorMessages));
			data=null;
			response = new WebServiceResponse(smithStatus, data, error);
			return new ResponseEntity<>(response, HttpStatus.OK);
		 }
	}

	
}
