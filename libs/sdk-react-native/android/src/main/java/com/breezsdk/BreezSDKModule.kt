package com.breezsdk;

import breez_sdk.*;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter

class BreezSDKModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {  
    override fun getName(): String {
        return "BreezSDK";
    }

    @ReactMethod
    fun addListener(eventName: String) {}

    @ReactMethod
    fun removeListeners(count: Int) {}

    @ReactMethod
    fun mnemonicToSeed(mnemonic: String, promise: Promise) {
        try {
            var seed = mnemonicToSeed(mnemonic);
            promise.resolve(readableArrayOf(seed));
        } catch (e: SdkException) {
            e.printStackTrace();
            promise.reject(e);
        }
    }

    @ReactMethod
    fun parseInput(input: String, promise: Promise) {
        try {
            var inputType = parseInput(input);
            promise.resolve(readableMapOf(inputType));
        } catch (e: SdkException) {
            e.printStackTrace();
            promise.reject(e);
        }
    }

    @ReactMethod
    fun parseInvoice(invoice: String, promise: Promise) {
        try {
            var lnInvoice = parseInvoice(invoice);
            promise.resolve(readableMapOf(lnInvoice));
        } catch (e: SdkException) {
            e.printStackTrace();
            promise.reject(e);
        }
    }

    @ReactMethod
    fun startLogStream(promise: Promise) {
        try {
            var emitter = reactApplicationContext
                    .getJSModule(RCTDeviceEventEmitter::class.java)

            setLogStream(BreezSDKLogStream(emitter));
            promise.resolve("Log stream started");
        } catch (e: SdkException) {
            e.printStackTrace();
            promise.reject(e);
        }
    }
}
