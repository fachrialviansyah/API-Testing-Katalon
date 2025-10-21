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

// step POST
def x = 'responsePOST'

def angkaRomawi = ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X']
lastID = 10
for (int i = 0; i < angkaRomawi.size(); i++) {
    lastID = 1

    def firstName = 'Raja'
    def lastName = 'Sultan ' + "${angkaRomawi[i]}"
    def mail = 'RajaSultan' + "${angkaRomawi[i]}" + '@gmail.com'
    def avatar = 'https://ImageSultanRaja' + "${angkaRomawi[i]}" + '.com'
    def responsePOST = WS.sendRequest(findTestObject('mockAPI/03 Post User', [('firstName') : firstName, ('lastName') : lastName
                , ('email') : mail, ('avatar') : avatar]))

    WS.verifyResponseStatusCode(responsePOST, 201)
    WS.verifyElementPropertyValue(responsePOST, 'firstName', firstName)
    WS.verifyElementPropertyValue(responsePOST, 'lastName', lastName)
    WS.verifyElementPropertyValue(responsePOST, 'email', mail)
    WS.verifyElementPropertyValue(responsePOST, 'avatar', avatar)
}

println(lastID)

// Step Get
def responseGet = WS.sendRequest(findTestObject('mockAPI/01 Get User All'))

WS.verifyResponseStatusCode(responseGet, 200)

def firstName = WS.getElementPropertyValue(responseGet, 'firstName')
def lastName = WS.getElementPropertyValue(responseGet, 'lastName')
def mail = WS.getElementPropertyValue(responseGet, 'mail')

WS.verifyElementPropertyValue(responseGet, 'firstName', firstName)
WS.verifyElementPropertyValue(responseGet, 'lastName', lastName)
WS.verifyElementPropertyValue(responseGet, 'mail', mail)

// Step PUT
def responsePUT = WS.sendRequest(findTestObject('mockAPI/04 Put User', [('id') : lastID, ('firstName') : 'Raja Update', ('lastName') : 'Sultan Baru'
            , ('email') : 'RajaSultanUpdate@example.org', ('avatar') : 'https://ImageRajaUpdate.com']))

WS.verifyResponseStatusCode(responsePUT, 200)

def firstNamePUT = WS.getElementPropertyValue(responsePUT, 'firstName')
def lastNamePUT = WS.getElementPropertyValue(responsePUT, 'lastName')
def mailPUT = WS.getElementPropertyValue(responsePUT, 'mail')
def avatarPUt = WS.getElementPropertyValue(responsePUT, 'avatar')

WS.verifyElementPropertyValue(responsePUT, 'firstName', firstNamePUT)
WS.verifyElementPropertyValue(responsePUT, 'lastName', lastNamePUT)
WS.verifyElementPropertyValue(responsePUT, 'mail', mailPUT)
WS.verifyElementPropertyValue(responsePUT, 'avatar', avatarPUt)

// Step Delete
def responseDelete = WS.sendRequest(findTestObject('mockAPI/05 Delete User By ID', [('id') : '10']))

WS.verifyResponseStatusCode(responseDelete, 200)

def firstNameDelete = WS.getElementPropertyValue(responseDelete, 'firstName')
def lastNameDelete = WS.getElementPropertyValue(responseDelete, 'lastName')

WS.verifyElementPropertyValue(responseDelete, 'firstName', firstNameDelete)
WS.verifyElementPropertyValue(responseDelete, 'lastName', lastNameDelete)

// Step Get ByID
def responseGetById = WS.sendRequest(findTestObject('mockAPI/02 Get User By ID', [('id') : '10']))
WS.verifyResponseStatusCode(responseGetById, 404)

