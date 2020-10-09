use super::data::DataPtr;
use super::result::CResult;
use super::string::*;
use crate::panic::{handle_exception_result, ToResult};
use crate::ptr::{RPtr, RPtrRepresentable};
use cardano_serialization_lib::crypto::{LegacyDaedalusPrivateKey};

#[no_mangle]
pub unsafe extern "C" fn legacy_daedalus_private_key_from_bytes(
  data: *const u8, len: usize, result: &mut RPtr, error: &mut CharPtr
) -> bool {
  handle_exception_result(|| {
    LegacyDaedalusPrivateKey::from_bytes(std::slice::from_raw_parts(data, len)).into_result()
  })
  .map(|legacy_daedalus_private_key| legacy_daedalus_private_key.rptr())
  .response(result, error)
}