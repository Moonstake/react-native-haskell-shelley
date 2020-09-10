package io.emurgo.rnhaskellshelley;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class HaskellShelleyModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public HaskellShelleyModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "HaskellShelley";
    }

    // Utils

    @ReactMethod
    public final void makeIcarusBootstrapWitness(String txBodyHash, String addr, String key, Promise promise) {
        Native.I
                .makeIcarusBootstrapWitness(new RPtr(txBodyHash), new RPtr(addr), new RPtr(key))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void makeVkeyWitness(String txBodyHash, String sk, Promise promise) {
        Native.I
                .makeVkeyWitness(new RPtr(txBodyHash), new RPtr(sk))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void hashTransaction(String txBody, Promise promise) {
        Native.I
                .hashTransaction(new RPtr(txBody))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // BigNum

    @ReactMethod
    public final void bigNumFromStr(String string, Promise promise) {
        Native.I
                .bigNumFromStr(string)
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bigNumToStr(String bigNum, Promise promise) {
        Native.I
                .bigNumToStr(new RPtr(bigNum))
                .pour(promise);
    }

    @ReactMethod
    public final void bigNumCheckedAdd(String bigNumPtr, String otherPtr, Promise promise) {
        Native.I
                .bigNumCheckedAdd(new RPtr(bigNumPtr), new RPtr(otherPtr))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bigNumCheckedSub(String bigNumPtr, String otherPtr, Promise promise) {
        Native.I
                .bigNumCheckedSub(new RPtr(bigNumPtr), new RPtr(otherPtr))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // PublicKey

    @ReactMethod
    public final void publicKeyFromBech32(String bech32, Promise promise) {
        Native.I.publicKeyFromBech32(bech32)
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void publicKeyToBech32(String pubPtr, Promise promise) {
        Native.I
                .publicKeyToBech32(new RPtr(pubPtr))
                .pour(promise);
    }

    @ReactMethod
    public final void publicKeyFromBytes(String bytes, Promise promise) {
        Native.I
                .publicKeyFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void publicKeyAsBytes(String pubPtr, Promise promise) {
        Native.I
                .publicKeyAsBytes(new RPtr(pubPtr))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    // @ReactMethod
    // public final void publicKeyVerify(String pubPtr, String data, String signature, Promise promise) {
    //     Native.I
    //             .publicKeyVerify(new RPtr(pubPtr), Base64.decode(data, Base64.DEFAULT), new RPtr(signature))
    //             .pour(promise);
    // }

    @ReactMethod
    public final void publicKeyHash(String pubPtr, Promise promise) {
        Native.I
                .publicKeyHash(new RPtr(pubPtr))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // PrivateKey

    @ReactMethod
    public final void privateKeyToPublic(String prvPtr, Promise promise) {
        Native.I
                .privateKeyToPublic(new RPtr(prvPtr))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void privateKeyAsBytes(String prvPtr, Promise promise) {
        Native.I
                .privateKeyAsBytes(new RPtr(prvPtr))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void privateKeyFromExtendedBytes(String bytes, Promise promise) {
        Native.I
                .privateKeyFromExtendedBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // Bip32PublicKey

    @ReactMethod
    public final void bip32PublicKeyDerive(String bip32PublicKey, Double index, Promise promise) {
        Native.I
                .bip32PublicKeyDerive(new RPtr(bip32PublicKey), index.longValue())
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PublicKeyToRawKey(String bip32PublicKey, Promise promise) {
        Native.I
                .bip32PublicKeyToRawKey(new RPtr(bip32PublicKey))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PublicKeyFromBytes(String bytes, Promise promise) {
        Native.I
                .bip32PublicKeyFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PublicKeyAsBytes(String bip32PublicKey, Promise promise) {
        Native.I
                .bip32PublicKeyAsBytes(new RPtr(bip32PublicKey))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PublicKeyFromBech32(String bech32Str, Promise promise) {
        Native.I
                .bip32PublicKeyFromBech32(bech32Str)
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PublicKeyToBech32(String bip32PublicKey, Promise promise) {
        Native.I
                .bip32PublicKeyToBech32(new RPtr(bip32PublicKey))
                .pour(promise);
    }


    // Bip32PrivateKey

    @ReactMethod
    public final void bip32PrivateKeyDerive(String bip32PrivateKey, Double index, Promise promise) {
        Native.I
                .bip32PrivateKeyDerive(new RPtr(bip32PrivateKey), index.longValue())
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PrivateKeyGenerateEd25519Bip32(Promise promise) {
        Native.I
                .bip32PrivateKeyGenerateEd25519Bip32()
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PrivateKeyToRawKey(String bip32PrivateKey, Promise promise) {
        Native.I
                .bip32PrivateKeyToRawKey(new RPtr(bip32PrivateKey))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PrivateKeyToPublic(String bip32PrivateKey, Promise promise) {
        Native.I
                .bip32PrivateKeyToPublic(new RPtr(bip32PrivateKey))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PrivateKeyFromBytes(String bytes, Promise promise) {
        Native.I
                .bip32PrivateKeyFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PrivateKeyAsBytes(String bip32PrivateKey, Promise promise) {
        Native.I
                .bip32PrivateKeyAsBytes(new RPtr(bip32PrivateKey))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PrivateKeyFromBech32(String bech32Str, Promise promise) {
        Native.I
                .bip32PrivateKeyFromBech32(bech32Str)
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PrivateKeyToBech32(String bip32PrivateKey, Promise promise) {
        Native.I
                .bip32PrivateKeyToBech32(new RPtr(bip32PrivateKey))
                .pour(promise);
    }

    @ReactMethod
    public final void bip32PrivateKeyFromBip39Entropy(String entropy, String password, Promise promise) {
        Native.I
                .bip32PrivateKeyFromBip39Entropy(Base64.decode(entropy, Base64.DEFAULT), Base64.decode(password, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // ByronAddress

    @ReactMethod
    public final void byronAddressToBase58(String byronAddress, Promise promise) {
        Native.I
                .byronAddressToBase58(new RPtr(byronAddress))
                .pour(promise);
    }

    @ReactMethod
    public final void byronAddressFromBase58(String string, Promise promise) {
        Native.I
                .byronAddressFromBase58(string)
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void byronAddressIsValid(String string, Promise promise) {
        Native.I
                .byronAddressIsValid(string)
                .pour(promise);
    }

    @ReactMethod
    public final void byronAddressFromAddress(String address, Promise promise) {
        Native.I
                .byronAddressFromAddress(new RPtr(address))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void byronAddressToAddress(String byronAddress, Promise promise) {
        Native.I
                .byronAddressToAddress(new RPtr(byronAddress))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // Address

    @ReactMethod
    public final void addressToBytes(String address, Promise promise) {
        Native.I
                .addressToBytes(new RPtr(address))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void addressFromBytes(String bytes, Promise promise) {
        Native.I
                .addressFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void addressToBech32(String address, Promise promise) {
        Native.I
                .addressToBech32(new RPtr(address))
                .pour(promise);
    }

    @ReactMethod
    public final void addressToBech32WithPrefix(String address, String prefix, Promise promise) {
        Native.I
                .addressToBech32WithPrefix(new RPtr(address), prefix)
                .pour(promise);
    }

    @ReactMethod
    public final void addressFromBech32(String string, Promise promise) {
        Native.I
                .addressFromBech32(string)
                .map(RPtr::toJs)
                .pour(promise);
    }

    // Ed25519KeyHash

    @ReactMethod
    public final void ed25519KeyHashToBytes(String ed25519KeyHash, Promise promise) {
        Native.I
                .ed25519KeyHashToBytes(new RPtr(ed25519KeyHash))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void ed25519KeyHashFromBytes(String bytes, Promise promise) {
        Native.I
                .ed25519KeyHashFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // TransactionHash

    @ReactMethod
    public final void transactionHashToBytes(String transactionHash, Promise promise) {
        Native.I
                .transactionHashToBytes(new RPtr(transactionHash))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionHashFromBytes(String bytes, Promise promise) {
        Native.I
                .transactionHashFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // StakeCredential

    @ReactMethod
    public final void stakeCredentialFromKeyHash(String keyHash, Promise promise) {
        Native.I
                .stakeCredentialFromKeyHash(new RPtr(keyHash))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void stakeCredentialToKeyHash(String stakeCredential, Promise promise) {
        Native.I
                .stakeCredentialToKeyHash(new RPtr(stakeCredential))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void stakeCredentialKind(String stakeCredential, Promise promise) {
        Native.I
                .stakeCredentialKind(new RPtr(stakeCredential))
                .pour(promise);
    }

    @ReactMethod
    public final void stakeCredentialToBytes(String stakeCredential, Promise promise) {
        Native.I
                .stakeCredentialToBytes(new RPtr(stakeCredential))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void stakeCredentialFromBytes(String bytes, Promise promise) {
        Native.I
                .stakeCredentialFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // StakeRegistration

    @ReactMethod
    public final void stakeRegistrationNew(String stakeCredential, Promise promise) {
        Native.I
                .stakeRegistrationNew(new RPtr(stakeCredential))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void stakeRegistrationStakeCredential(String stakeRegistration, Promise promise) {
        Native.I
                .stakeRegistrationStakeCredential(new RPtr(stakeRegistration))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void stakeRegistrationToBytes(String stakeRegistration, Promise promise) {
        Native.I
                .stakeRegistrationToBytes(new RPtr(stakeRegistration))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void stakeRegistrationFromBytes(String bytes, Promise promise) {
        Native.I
                .stakeRegistrationFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // StakeDeregistration

    @ReactMethod
    public final void stakeDeregistrationNew(String stakeCredential, Promise promise) {
        Native.I
                .stakeDeregistrationNew(new RPtr(stakeCredential))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void stakeDeregistrationStakeCredential(String stakeDeregistration, Promise promise) {
        Native.I
                .stakeDeregistrationStakeCredential(new RPtr(stakeDeregistration))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void stakeDeregistrationToBytes(String stakeDeregistration, Promise promise) {
        Native.I
                .stakeDeregistrationToBytes(new RPtr(stakeDeregistration))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void stakeDeregistrationFromBytes(String bytes, Promise promise) {
        Native.I
                .stakeDeregistrationFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // StakeDelegation

    @ReactMethod
    public final void stakeDelegationNew(String stakeCredential, String poolKeyhash, Promise promise) {
        Native.I
                .stakeDelegationNew(new RPtr(stakeCredential), new RPtr(poolKeyhash))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void stakeDelegationStakeCredential(String stakeDelegation, Promise promise) {
        Native.I
                .stakeDelegationStakeCredential(new RPtr(stakeDelegation))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void stakeDelegationPoolKeyhash(String poolKeyHash, Promise promise) {
        Native.I
                .stakeDelegationPoolKeyhash(new RPtr(poolKeyHash))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void stakeDelegationToBytes(String stakeDelegation, Promise promise) {
        Native.I
                .stakeDelegationToBytes(new RPtr(stakeDelegation))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void stakeDelegationFromBytes(String bytes, Promise promise) {
        Native.I
                .stakeDelegationFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // Certificate

    @ReactMethod
    public final void certificateNewStakeRegistration(String stakeRegistration, Promise promise) {
        Native.I
                .certificateNewStakeRegistration(new RPtr(stakeRegistration))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void certificateNewStakeDeregistration(String stakeDeregistration, Promise promise) {
        Native.I
                .certificateNewStakeDeregistration(new RPtr(stakeDeregistration))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void certificateNewStakeDelegation(String stakeDelegation, Promise promise) {
        Native.I
                .certificateNewStakeDelegation(new RPtr(stakeDelegation))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void certificateToBytes(String certificate, Promise promise) {
        Native.I
                .certificateToBytes(new RPtr(certificate))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void certificateFromBytes(String bytes, Promise promise) {
        Native.I
                .certificateFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // Certificates

    @ReactMethod
    public final void certificatesToBytes(String certificates, Promise promise) {
        Native.I
                .certificatesToBytes(new RPtr(certificates))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void certificatesFromBytes(String bytes, Promise promise) {
        Native.I
                .certificatesFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void certificatesNew(Promise promise) {
        Native.I
                .certificatesNew()
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void certificatesLen(String certificates, Promise promise) {
        Native.I
                .certificatesLen(new RPtr(certificates))
                .map(Long::intValue)
                .pour(promise);
    }

    @ReactMethod
    public final void certificatesAdd(String certificates, String item, Promise promise) {
        Native.I
                .certificatesAdd(new RPtr(certificates), new RPtr(item))
                .pour(promise);
    }

    // BaseAddress

    @ReactMethod
    public final void baseAddressNew(Integer network, String payment, String stake, Promise promise) {
        Native.I
                .baseAddressNew(network, new RPtr(payment), new RPtr(stake))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void baseAddressPaymentCred(String baseAddress, Promise promise) {
        Native.I
                .baseAddressPaymentCred(new RPtr(baseAddress))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void baseAddressStakeCred(String baseAddress, Promise promise) {
        Native.I
                .baseAddressStakeCred(new RPtr(baseAddress))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void baseAddressToAddress(String baseAddress, Promise promise) {
        Native.I
                .baseAddressToAddress(new RPtr(baseAddress))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void baseAddressFromAddress(String address, Promise promise) {
        Native.I
                .baseAddressFromAddress(new RPtr(address))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // RewardAddress

    @ReactMethod
    public final void rewardAddressNew(Integer network, String payment, Promise promise) {
        Native.I
                .rewardAddressNew(network, new RPtr(payment))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void rewardAddressPaymentCred(String rewardAddress, Promise promise) {
        Native.I
                .rewardAddressPaymentCred(new RPtr(rewardAddress))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void rewardAddressToAddress(String rewardAddress, Promise promise) {
        Native.I
                .rewardAddressToAddress(new RPtr(rewardAddress))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void rewardAddressFromAddress(String address, Promise promise) {
        Native.I
                .rewardAddressFromAddress(new RPtr(address))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // UnitInterval

    @ReactMethod
    public final void unitIntervalToBytes(String unitInterval, Promise promise) {
        Native.I
                .unitIntervalToBytes(new RPtr(unitInterval))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void unitIntervalFromBytes(String bytes, Promise promise) {
        Native.I
                .unitIntervalFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void unitIntervalNew(String numerator, String denominator, Promise promise) {
        Native.I
                .unitIntervalNew(new RPtr(numerator), new RPtr(denominator))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // TransactionInput

    @ReactMethod
    public final void transactionInputToBytes(String transactionInput, Promise promise) {
        Native.I
                .transactionInputToBytes(new RPtr(transactionInput))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionInputFromBytes(String bytes, Promise promise) {
        Native.I
                .transactionInputFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionInputTransactionId(String transactionInput, Promise promise) {
        Native.I
                .transactionInputTransactionId(new RPtr(transactionInput))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionInputIndex(String transactionInput, Promise promise) {
        Native.I
                .transactionInputIndex(new RPtr(transactionInput))
                .map(Long::intValue)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionInputNew(String transactionId, Double index, Promise promise) {
        Native.I
                .transactionInputNew(new RPtr(transactionId), index.longValue())
                .map(RPtr::toJs)
                .pour(promise);
    }

    // TransactionOutput

    @ReactMethod
    public final void transactionOutputToBytes(String transactionOutput, Promise promise) {
        Native.I
                .transactionOutputToBytes(new RPtr(transactionOutput))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionOutputFromBytes(String bytes, Promise promise) {
        Native.I
                .transactionOutputFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionOutputNew(String address, String amount, Promise promise) {
        Native.I
                .transactionOutputNew(new RPtr(address), new RPtr(amount))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionOutputAmount(String transactionOutput, Promise promise) {
        Native.I
                .transactionOutputAmount(new RPtr(transactionOutput))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionOutputAddress(String transactionOutput, Promise promise) {
        Native.I
                .transactionOutputAddress(new RPtr(transactionOutput))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // TransactionOutputs

    @ReactMethod
    public final void transactionOutputsLen(String transactionOutputs, Promise promise) {
        Native.I
                .transactionOutputsLen(new RPtr(transactionOutputs))
                .map(Long::intValue)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionOutputsGet(String transactionOutputs, Integer index, Promise promise) {
        Native.I
                .transactionOutputsGet(new RPtr(transactionOutputs), index)
                .map(RPtr::toJs)
                .pour(promise);
    }


    // LinearFee

    @ReactMethod
    public final void linearFeeCoefficient(String linearFee, Promise promise) {
        Native.I
                .linearFeeCoefficient(new RPtr(linearFee))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void linearFeeConstant(String linearFee, Promise promise) {
        Native.I
                .linearFeeConstant(new RPtr(linearFee))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void linearFeeNew(String coefficient, String constant, Promise promise) {
        Native.I
                .linearFeeNew(new RPtr(coefficient), new RPtr(constant))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // Vkeywitnesses

    @ReactMethod
    public final void vkeywitnessesNew(Promise promise) {
        Native.I
                .vkeywitnessesNew()
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void vkeywitnessesLen(String vkwitnesses, Promise promise) {
        Native.I
                .vkeywitnessesLen(new RPtr(vkwitnesses))
                .map(Long::intValue)
                .pour(promise);
    }

    @ReactMethod
    public final void vkeywitnessesAdd(String vkwitnesses, String item, Promise promise) {
        Native.I
                .vkeywitnessesAdd(new RPtr(vkwitnesses), new RPtr(item))
                .pour(promise);
    }

    // BootstrapWitnesses

    @ReactMethod
    public final void bootstrapWitnessesNew(Promise promise) {
        Native.I
                .bootstrapWitnessesNew()
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void bootstrapWitnessesLen(String witnesses, Promise promise) {
        Native.I
                .bootstrapWitnessesLen(new RPtr(witnesses))
                .map(Long::intValue)
                .pour(promise);
    }

    @ReactMethod
    public final void bootstrapWitnessesAdd(String witnesses, String item, Promise promise) {
        Native.I
                .bootstrapWitnessesAdd(new RPtr(witnesses), new RPtr(item))
                .pour(promise);
    }

    // TransactionWitnessSet

    @ReactMethod
    public final void transactionWitnessSetNew(Promise promise) {
        Native.I
                .transactionWitnessSetNew()
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionWitnessSetSetVkeys(String witnessSet, String vkeys, Promise promise) {
        Native.I
                .transactionWitnessSetSetVkeys(new RPtr(witnessSet), new RPtr(vkeys))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionWitnessSetSetBootstraps(String witnessSet, String bootstraps, Promise promise) {
        Native.I
                .transactionWitnessSetSetBootstraps(new RPtr(witnessSet), new RPtr(bootstraps))
                .pour(promise);
    }

    // TransactionBody

    @ReactMethod
    public final void transactionBodyToBytes(String transactionBody, Promise promise) {
        Native.I
                .transactionBodyToBytes(new RPtr(transactionBody))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBodyFromBytes(String bytes, Promise promise) {
        Native.I
                .transactionBodyFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBodyOutputs(String txBody, Promise promise) {
        Native.I
                .transactionBodyOutputs(new RPtr(txBody))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // Transaction

    @ReactMethod
    public final void transactionBody(String tx, Promise promise) {
        Native.I
                .transactionBody(new RPtr(tx))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionNew(String body, String witnessSet, Promise promise) {
        Native.I
                .transactionNew(new RPtr(body), new RPtr(witnessSet))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionToBytes(String transaction, Promise promise) {
        Native.I
                .transactionToBytes(new RPtr(transaction))
                .map(bytes -> Base64.encodeToString(bytes, Base64.DEFAULT))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionFromBytes(String bytes, Promise promise) {
        Native.I
                .transactionFromBytes(Base64.decode(bytes, Base64.DEFAULT))
                .map(RPtr::toJs)
                .pour(promise);
    }

    // TransactionBuilder

    @ReactMethod
    public final void transactionBuilderAddKeyInput(String txBuilder, String hash, String input, String amount, Promise promise) {
        Native.I
                .transactionBuilderAddKeyInput(new RPtr(txBuilder), new RPtr(hash), new RPtr(input), new RPtr(amount))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderAddBootstrapInput(String txBuilder, String hash, String input, String amount, Promise promise) {
        Native.I
                .transactionBuilderAddBootstrapInput(new RPtr(txBuilder), new RPtr(hash), new RPtr(input), new RPtr(amount))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderAddOutput(String txBuilder, String output, Promise promise) {
        Native.I
                .transactionBuilderAddOutput(new RPtr(txBuilder), new RPtr(output))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderSetFee(String txBuilder, String fee, Promise promise) {
        Native.I
                .transactionBuilderSetFee(new RPtr(txBuilder), new RPtr(fee))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderSetTtl(String txBuilder, Double ttl, Promise promise) {
        Native.I
                .transactionBuilderSetTtl(new RPtr(txBuilder), ttl.longValue())
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderSetCerts(String txBuilder, String certs, Promise promise) {
        Native.I
                .transactionBuilderSetCerts(new RPtr(txBuilder), new RPtr(certs))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderNew(String linearFee, String minimumUtxoVal, String poolDeposit, String keyDeposit, Promise promise) {
        Native.I
                .transactionBuilderNew(new RPtr(linearFee), new RPtr(minimumUtxoVal), new RPtr(poolDeposit), new RPtr(keyDeposit))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderGetExplicitInput(String txBuilder, Promise promise) {
        Native.I
                .transactionBuilderGetExplicitInput(new RPtr(txBuilder))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderGetImplicitInput(String txBuilder, Promise promise) {
        Native.I
                .transactionBuilderGetImplicitInput(new RPtr(txBuilder))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderGetExplicitOutput(String txBuilder, Promise promise) {
        Native.I
                .transactionBuilderGetExplicitOutput(new RPtr(txBuilder))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderGetDeposit(String txBuilder, Promise promise) {
        Native.I
                .transactionBuilderGetDeposit(new RPtr(txBuilder))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderGetFeeIfSet(String txBuilder, Promise promise) {
        Native.I
                .transactionBuilderGetFeeIfSet(new RPtr(txBuilder))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderAddChangeIfNeeded(String txBuilder, String address, Promise promise) {
        Native.I
                .transactionBuilderAddChangeIfNeeded(new RPtr(txBuilder), new RPtr(address))
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderBuild(String txBuilder, Promise promise) {
        Native.I
                .transactionBuilderBuild(new RPtr(txBuilder))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void transactionBuilderMinFee(String txBuilder, Promise promise) {
        Native.I
                .transactionBuilderMinFee(new RPtr(txBuilder))
                .map(RPtr::toJs)
                .pour(promise);
    }

    @ReactMethod
    public final void byronAddressFromIcarusKey(String bip32PublicKey, Integer network, Promise promise) {
        Native.I
                .byronAddressFromIcarusKey(new RPtr(bip32PublicKey), network)
                .map(RPtr::toJs)
                .pour(promise);
    }

}
