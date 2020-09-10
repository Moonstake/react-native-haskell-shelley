use jni::objects::{JObject, JString};
use jni::sys::{jobject, jboolean, jint};
use jni::JNIEnv;
use super::ptr_j::*;
use super::result::ToJniResult;
use super::string::*;
use super::primitive::ToPrimitiveObject;
use crate::panic::{handle_exception_result, ToResult};
use crate::ptr::RPtrRepresentable;
use cardano_serialization_lib::address::{Address, ByronAddress};
use cardano_serialization_lib::crypto::Bip32PublicKey;

#[allow(non_snake_case)]
#[no_mangle]
pub unsafe extern "C" fn Java_io_emurgo_rnhaskellshelley_Native_byronAddressToBase58(
  env: JNIEnv, _: JObject, ptr: JRPtr
) -> jobject {
  handle_exception_result(|| {
    let rptr = ptr.rptr(&env)?;
    let val = rptr.typed_ref::<ByronAddress>()?;
    val.to_base58().jstring(&env)
  })
  .jresult(&env)
}

#[allow(non_snake_case)]
#[no_mangle]
pub extern "C" fn Java_io_emurgo_rnhaskellshelley_Native_byronAddressFromBase58(
  env: JNIEnv, _: JObject, string: JString
) -> jobject {
  handle_exception_result(|| {
    let rstr = string.string(&env)?;
    let val = ByronAddress::from_base58(&rstr).into_result()?;
    val.rptr().jptr(&env)
  })
  .jresult(&env)
}

#[allow(non_snake_case)]
#[no_mangle]
pub unsafe extern "C" fn Java_io_emurgo_rnhaskellshelley_Native_byronAddressIsValid(
  env: JNIEnv, _: JObject, string: JString
) -> jobject {
  handle_exception_result(|| {
    let rstr = string.string(&env)?;
    let val = ByronAddress::is_valid(&rstr);
    (val as jboolean).jobject(&env)
  })
  .jresult(&env)
}

#[allow(non_snake_case)]
#[no_mangle]
pub unsafe extern "C" fn Java_io_emurgo_rnhaskellshelley_Native_byronAddressToAddress(
  env: JNIEnv, _: JObject, ptr: JRPtr
) -> jobject {
  handle_exception_result(|| {
    let rptr = ptr.rptr(&env)?;
    rptr
      .typed_ref::<ByronAddress>()
      .and_then(|byron_addr| byron_addr.to_address().rptr().jptr(&env))
  })
  .jresult(&env)
}

#[allow(non_snake_case)]
#[no_mangle]
pub unsafe extern "C" fn Java_io_emurgo_rnhaskellshelley_Native_byronAddressFromAddress(
  env: JNIEnv, _: JObject, address: JRPtr
) -> jobject {
  handle_exception_result(|| {
    let address = address.rptr(&env)?;
    address
      .typed_ref::<Address>()
      .map(|address| ByronAddress::from_address(address))
      .and_then(|byron_address| byron_address.rptr().jptr(&env))
  })
  .jresult(&env)
}
#[allow(non_snake_case)]
#[no_mangle]
pub unsafe extern "C" fn Java_io_emurgo_rnhaskellshelley_Native_byronAddressFromIcarusKey(
  env: JNIEnv, _: JObject, bip_32_public_key: JRPtr, network: jint
) -> jobject {
  handle_exception_result(|| {
    let bip_32_public_key = bip_32_public_key.rptr(&env)?;
    bip_32_public_key
      .typed_ref::<Bip32PublicKey>()
      .map(|bip_32_public_key| ByronAddress::from_icarus_key(bip_32_public_key, network as u8))
      .and_then(|byron_address| byron_address.rptr().jptr(&env))
  })
  .jresult(&env)
}

