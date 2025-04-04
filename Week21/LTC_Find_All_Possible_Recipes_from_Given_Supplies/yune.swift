class Solution {
    func findAllRecipes(_ recipes: [String], _ ingredients: [[String]], _ supplies: [String]) -> [String] {

        var supplies = supplies

        var recipeMap = recipes.enumerated().reduce(into: [String:Int]()) {
            $0[$1.1] = $1.0
        }

        var supplieMap = supplies.reduce(into: [String:Bool]()) {
            $0[$1] = true
        }

        var answer = [String]()

        while true {
            let prevCount = recipeMap.count
            
            outer: for (recipe, index) in recipeMap {
                for ingredient in ingredients[index] {
                    if supplieMap[ingredient] == nil {
                        continue outer
                    }
                }

            answer.append(recipe)
            supplieMap[recipe] = true
            recipeMap.removeValue(forKey: recipe)
            }
            if recipeMap.count == prevCount { break }
        }

        return answer
    }
}
