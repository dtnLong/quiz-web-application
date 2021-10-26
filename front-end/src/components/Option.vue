<template>
    


    <p class='w-full px-10 py-5 text-gray-900 transition-all duration-100 border border-gray-900 cursor-pointer select-none rounded-2xl hover:border-error-500 hover:bg-error-500 hover:text-white' :class="{ selected : isSelected}" @click="optionSelected" style="font-size: 18px">
        {{option}}. {{optionText}}
    </p>
    
</template>

<script>
import {computed} from 'vue'

export default {
    props: {
        optionText: String,
        questionNumber: Number,
        option: String,
        hasChecked: Boolean,  
         },
    emits: ["optionSelected"],
    setup(props, {emit}) {
         
        let isSelected = computed(()=> props.hasChecked);
        
        
        const optionSelected = () =>{
            let selection = {option: props.option, optionText: props.optionText};
            emit("optionSelected", selection);
            //isSelected.value = true;
        }
        
        return{ optionSelected, isSelected}
    },
}
</script>
