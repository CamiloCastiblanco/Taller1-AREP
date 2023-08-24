
function visualizer($node) {
    var base = this;

   
    base.node = $node;

    
    base.transform = {
        "<>": "div",
        class: "visual-package visual-${show} visual-${type}",
        html: [
            {
                "<>": "div",
                class: "visual-header",
                html: [
                    {
                        "<>": "div",
                        class: function (obj) {
                            var classes = ["visual-arrow"];

                            if (base.getValue(obj.value) !== undefined) classes.push("hide");

                            return classes.join(" ");
                        },
                    },
                    { "<>": "span", class: "visual-name", text: "${name}" },
                    {
                        "<>": "span",
                        class: "visual-value",
                        text: function (obj) {
                            var value = base.getValue(obj.value);
                            if (value !== undefined) return " : " + value;
                            else return "";
                        },
                    },
                ],
            },
            {
                "<>": "div",
                class: "visual-children",
                html: function (obj) {
                    return base.children(obj.value);
                },
            },
        ],
    };
}

visualizer.prototype = {
    visualize: function (json) {
        var base = this;

        
        base.node
            .empty()
            .json2html(base.convert("json", json, "open"), base.transform);

      
        base.events();
    },

    
    getValue: function (obj) {
        var type = $.type(obj);

        
        switch (type) {
            case "array":
            case "object":
                return undefined;
                break;

            case "function":
                
                return "function";
                break;

            case "string":
                return "'" + obj + "'";
                break;

            default:
                return obj;
                break;
        }
    },

   
    children: function (obj) {
        var base = this;

        var type = $.type(obj);

      
        switch (type) {
            case "array":
            case "object":
                return json2html.transform(obj, base.transform);
                break;

            default:
               
                break;
        }
    },

   
    convert: function (name, obj, show) {
        var base = this;

        var type = $.type(obj);

        if (show === undefined) show = "closed";

        var children = [];

       
        switch (type) {
            case "array":
               
                var len = obj.length;
                for (var j = 0; j < len; ++j) {
                   
                    children[j] = base.convert(j, obj[j]);
                }
                break;

            case "object":
              
                var j = 0;
                for (var prop in obj) {
                    children[j] = base.convert(prop, obj[prop]);
                    j++;
                }
                break;

            default:
                
                children = obj;
                break;
        }

        return { name: name, value: children, type: type, show: show };
    },

   
    events: function () {
        var base = this;

       
        base.node.find(".visual-header").click(function () {
            var $parent = $(this).parent();

           
            if ($parent.hasClass("visual-closed")) {
                $parent.removeClass("visual-closed");
                $parent.addClass("visual-open");
            } else {
                $parent.removeClass("visual-open");
                $parent.addClass("visual-closed");
            }
        });
    },
};
