const fs = require('fs')
const path = require('path')

const tiers = ["absolute_overclocked","supreme_quantum","cosmic_dense","infinite_multiversal"]
const factorys = ["alloying","smelting","enriching","crushing","compressing","combining","purifying","injecting","infusing","sawing"]

const mode = process.argv[2] // コマンドライン引数

for (const tier of tiers) {
    for (const factory of factorys) {
        const filePath = `src/main/resources/assets/emextras/models/item/${tier}_${factory}_factory.json`
        if (mode === "delete") {
            if (fs.existsSync(filePath)) {
                fs.unlinkSync(filePath)
                console.log(`Deleted: ${filePath}`)
            }
        } else {
            const itemmodel = {
                "parent": `emextras:block/factory/${factory}/${tier}`
            }
            const json = JSON.stringify(itemmodel, null, 4)
            fs.mkdirSync(path.dirname(filePath), { recursive: true })
            fs.writeFileSync(filePath, json)
            console.log(`Created: ${filePath}`)
        }
    }
}
//const fs = require('fs')
//
//const tiers = ["absolute_overclocked","supreme_quantum","cosmic_dense","infinite_multiversal"]
//const factorys = ["alloying","smelting","enriching","crushing","compressing","combining","purifying","injecting","infusing","sawing"]
//
//for (const tier of tiers) {
//    for (const factory of factorys) {
//        const itemmodel = {
//            "parent": `emextras:block/factory/${factory}/${tier}`
//        }
//        const json = JSON.stringify(itemmodel, null, 4)
//        const path = `src/main/resources/assets/emextras/models/item/${tier}_${factory}_factory.json`
//        fs.writeFileSync(path, json)
//    }
//}