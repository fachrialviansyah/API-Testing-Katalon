import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//step POST
def testfirstName = 'ari'
def testlastName = 'kuy'
//random email
def randomString = generateRandomString(10)
def testemail = randomString + '@oke.tv'

def responsePost = WS.sendRequest(findTestObject('Dummy Api/Post User', [('firstName') : testfirstName, ('lastName') : testlastName
            , ('email') : testemail]))

WS.verifyResponseStatusCode(responsePost, 200)

WS.verifyElementPropertyValue(responsePost, 'firstName', testfirstName)
WS.verifyElementPropertyValue(responsePost, 'lastName', testlastName)

def newUserID = WS.getElementPropertyValue(responsePost, 'id')
def val_fm = WS.getElementPropertyValue(responsePost, 'firstName')
def val_lm = WS.getElementPropertyValue(responsePost, 'lastName')
def val_mail = WS.getElementPropertyValue(responsePost, 'email')

println("id: " + newUserID + " " + "firstName: " + val_fm + " " + "lastName: " + val_lm + " " + "email: " + val_mail)

// step PUT
def fnameUpdate = 'ariUpdate'
def lnameUpdate = 'kuyUpdate'

def responsePUT = WS.sendRequest(findTestObject('Dummy Api/Put User', [('id') : newUserID, ('firstName') : fnameUpdate, ('lastName') : lnameUpdate]))

WS.verifyResponseStatusCode(responsePUT, 200)

WS.verifyElementPropertyValue(responsePUT, 'firstName', fnameUpdate)
WS.verifyElementPropertyValue(responsePUT, 'lastName', lnameUpdate)

def val_fm_updt = WS.getElementPropertyValue(responsePUT, 'firstName')
def val_lm_updt = WS.getElementPropertyValue(responsePUT, 'lastName')
def val_mail_updt = WS.getElementPropertyValue(responsePUT, 'email')

println("id: " + newUserID + " " + "firstName: " + val_fm_updt + " " + "lastName: " + val_lm_updt + " " + "email: " + val_mail_updt)

// step GET
def responseGet = WS.sendRequest(findTestObject('Dummy Api/Get User By ID', [('id') : newUserID]))

WS.verifyResponseStatusCode(responseGet, 200)
WS.verifyElementPropertyValue(responseGet, 'firstName', fnameUpdate)
WS.verifyElementPropertyValue(responseGet, 'lastName', lnameUpdate)
WS.verifyElementPropertyValue(responseGet, 'email', val_mail_updt)

def val_fm_get = WS.getElementPropertyValue(responseGet, 'firstName')
def val_lm_get = WS.getElementPropertyValue(responseGet, 'lastName')
def val_mail_get = WS.getElementPropertyValue(responseGet, 'email')

println("id: " + newUserID + " " + "firstName: " + val_fm_get + " " + "lastName: " + val_lm_get + " " + "email: " + val_mail_get)

// step DELETE
def responseDelete = WS.sendRequest(findTestObject('Dummy Api/Delete User By ID', [('id') : newUserID]))

WS.verifyResponseStatusCode(responseDelete, 200)
WS.verifyElementPropertyValue(responseDelete, 'id', newUserID)

def val_fm_del = WS.getElementPropertyValue(responseDelete, 'id')
println("delete id user: " + val_fm_del)

def generateRandomString(int length) {
    def charPool = ((('a'..'z') + ('A'..'Z')) + ('0'..'9')).join()

    def random = new Random()

    return (1..length).collect({ 
            charPool[random.nextInt(charPool.length())]
        }).join()
}

