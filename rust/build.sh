#cd /rust
#run sh build.sh
#copy 2 file (1) include/react_native_haskell_shelley.h 
#            (2) rust/target/universal/release/libreact_native_haskell_shelley.a
#             into 2 file above move to ios/rust
cargo lipo --release