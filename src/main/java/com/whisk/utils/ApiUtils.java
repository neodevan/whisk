package com.whisk.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;






import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.http.entity.InputStreamEntity;


/**
 * Created by Vipin Alias Neo De Van
 */
public class ApiUtils {
	// Global Setup Variables
	public static String path;
	public static String jsonPathTerm;
	public static String accessToken = "CZQPXwzzFgkezRPDv1c8VzX4kKqZiZ9ebfW9QEw5cLHpUTBqvHap7ipTrjW3u0Me";

	// Sets Base URI
	public static void setBaseURI() {
		RestAssured.baseURI = "https://my.whisk-dev.com/";
	}


	// Reset Base URI (after test)
	public static void resetBaseURI() {
		RestAssured.baseURI = null;
	}

	// Reset base path
	public static void resetBasePath() {
		RestAssured.basePath = null;
	}

	// Sets ContentType
	public static void setContentType(ContentType Type) {
		given().contentType(Type);
	}

	// Returns response by given path
	public static Response getShoppingList(String id) {
		return given().contentType(ContentType.JSON).header("Authorization", "Bearer "+accessToken).when().get("list/v2/{id}",id);
	}
	
	public static Response createShoppingList(String shoppingList) {
		return given().contentType(ContentType.JSON).header("Authorization", "Bearer "+accessToken).body(createShoppingListPayLoad(shoppingList)).when().post("/list/v2");
	}

	public static Response deleteShoppingList(String id) {
		return given().contentType(ContentType.JSON).header("Authorization", "Bearer "+accessToken).when().delete("list/v2/{id}",id);
	}

	public static String createShoppingListPayLoad(String shoppingList){
		return "{ \n" +
				"  \"name\": \""+shoppingList+"\",\n" +
				"  \"primary\": false\n" +
				" }";
	}

	// Returns response
	public static Response getResponse() {
		return get();
	}

	

}