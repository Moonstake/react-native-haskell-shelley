use super::ptr_j::*;
use super::result::ToJniResult;
use super::string::ToJniString;
use super::string::ToString;
use crate::panic::{handle_exception_result, ToResult, Zip};
use crate::ptr::RPtrRepresentable;
use jni::objects::{JObject, JString};
use jni::sys::{jbyteArray, jlong, jobject};
use jni::JNIEnv;
use cardano_serialization_lib::crypto::{LegacyDaedalusPrivateKey};
use std::convert::TryFrom;


#[allow(non_snake_case)]
#[no_mangle]
pub unsafe extern "C" fn Java_io_emurgo_rnhaskellshelley_Native_legacyDaedalusPrivateKeyFromBytes(
  env: JNIEnv, _: JObject, bytes: jbyteArray
) -> jobject {
  handle_exception_result(|| {
    env
      .convert_byte_array(bytes)
      .into_result()
      .and_then(|bytes| LegacyDaedalusPrivateKey::from_bytes(&bytes).into_result())
      .and_then(|legacy_daedalus_private_key| legacy_daedalus_private_key.rptr().jptr(&env))
  })
  .jresult(&env)
}
