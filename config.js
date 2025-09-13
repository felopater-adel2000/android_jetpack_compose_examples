

export default {
  source: ["Foundations.fixed.js"],
  preprocessors: ["tokens-studio"],
  platforms: {
    Android: {
      buildPath: "app/build/generated/source/themes/com/maxab/themes/",
      prefix: "",
      transformGroup: "compose",
      transforms: [
        "color/composeColor",
        "size/compose/remToSp",
        "size/compose/remToDp",
        "size/compose/em",
      ],
      files: [
        {
            destination: "Colors.kt",
            format: "compose/object",
            options: {
                className: "Colors",
                outputReferences: false,
                accessControl: "public",
                showFileHeader: true,
                packageName: "com.maxab.theme"
            },
            filter: function (token) {
                return token.type === 'color' && token.name.startsWith('globalDefaultColors');
            },
        },
        {
            destination: "Scales.kt",
            format: "compose/object",
            options: {
                className: "Scales",
                outputReferences: true,
                accessControl: "public",
                showFileHeader: true,
                packageName: "com.maxab.theme"
            },
            filter: function (token) {
                return token.type === 'number' && token.name.startsWith('globalDefaultScale');
            },
        },
        {
            destination: "MarketPlaceSemantic.kt",
            format: "compose/object",
            options: {
                className: "MarketPlaceSemantic",
                outputReferences: false,
                accessControl: "public",
                showFileHeader: true,
                packageName: "com.maxab.theme",
            },
            filter: function (token) {
                //token.original.value = token.original.value.replace("{", "{global/default.")
                //console.log(typeof token.original.value)
                //console.log(token);
                return token.path[0] === "semantic/marketplace";
            },
        },
        {
            destination: "InstockSemantic.kt",
            format: "compose/object",
            options: {
                className: "InstockSemantic",
                outputReferences: false,
                accessControl: "public",
                showFileHeader: true,
                packageName: "com.maxab.theme",

            },
            filter: function (token) {
                return token.path[0] === "semantic/instock";
            },
        },
        {
            destination: "FintechSemantic.kt",
            format: "compose/object",
            options: {
                className: "FintechSemantic",
                outputReferences: false,
                accessControl: "public",
                showFileHeader: true,
                packageName: "com.maxab.theme",
            },
            filter: function (token) {
                return token.path[0] === "semantic/fintech";
            },
        },
        {
            destination: "SupplySemantic.kt",
            format: "compose/object",
            options: {
                className: "SupplySemantic",
                outputReferences: false,
                accessControl: "public",
                showFileHeader: true,
                packageName: "com.maxab.theme",
            },
            filter: function (token) {
                return token.path[0] === "semantic/supply";
            },
        },
        {
            destination: "SuperAppSemantic.kt",
            format: "compose/object",
            options: {
                className: "SuperAppSemantic",
                outputReferences: false,
                accessControl: "public",
                showFileHeader: true,
                packageName: "com.maxab.theme",
            },
            filter: function (token) {
                return token.path[0] === "semantic/superapp";
            },
        },
//        {
//            destination: "Typography.kt",
//            format: "compose/object",
//            options: {
//                className: "Typography",
//                outputReferences: false,
//                accessControl: "public",
//                showFileHeader: true,
//                packageName: "com.maxab.theme",
//            },
//            filter: function (token) {
//                return token.key.startsWith('{typography/default');
//            },
//        },
    ]
    }
  },
}