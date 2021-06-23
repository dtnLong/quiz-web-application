<template>
    <div class='text-xl'>
                     <input class='border-none outline-none' type="radio" :name="`question-${questionNumber}`" :id="option" :value="option" />

                    <label  :for="option">{{optionText}}</label> 
                                           
                </div>
</template>

<script>
import { computed, onMounted, onUpdated } from '@vue/runtime-core'
export default {
    props: {
        optionText: String,
        questionNumber: String,
        optionIndex: String,
        hasChecked: String,    },
    
    setup(props) {
        const option = computed(()=> {
            switch (props.optionIndex){
                case "0":
                    return "A";
                case "1":
                    return "B";
                case "2":
                    return "C";
                case "3":
                    return "D";

            }
        })
        onUpdated(()=> {
            if (props.hasChecked === option.value){
                document.getElementById(`${option.value}`).checked = true;
            }else {
                document.getElementById(`${option.value}`).checked = false;
            }
        })
        
        
        return{option}
    },
}
</script>