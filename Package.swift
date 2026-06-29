// swift-tools-version:5.5
import PackageDescription

let package = Package(
    name: "SuperkassaDelivery",
    platforms: [
        .iOS(.v15)
    ],
    products: [
        .library(
            name: "SuperkassaDelivery",
            targets: ["SuperkassaDelivery"]
        ),
    ],
    dependencies: [],
    targets: [
        .binaryTarget(
            name: "SuperkassaDelivery",
            url: "https://github.com/texport/superkassa-delivery/releases/download/v1.1.0/SuperkassaDelivery.xcframework.zip",
            checksum: "6ed611d4b0b38de097b376c45cc2d421bb3e70b74159e25077f826f5215f6dc4"
        )
    ]
)
