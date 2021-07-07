<template>
    <!-- <div class='text-xl'>
                     <input class='border-none outline-none' type="radio" :name="`question-${questionNumber}`" :id="option" :value="option" />

                    <label  :for="option">{{optionText}}</label> 
                                           
                </div> -->

    <div class='relative text-xl'>
        <input class='border-none outline-none opacity-0' type="radio" :id="option" :value="option" @change='$emit("optionSelected", option)' :name="`question-${questionNumber}`"/>
        <span class='absolute top-0 left-0 w-4 h-4 transform translate-y-1/2 bg-gray-300 rounded-full checked-mark'></span>
        <label class='ml-6 cursor-pointer select-none ' :for="option">{{optionText}}</label> 
                            
    </div> 
    
</template>

<script>

import {  onMounted, onUpdated } from '@vue/runtime-core'
export default {
    props: {
        optionText: String,
        questionNumber: String,
        option: String,
        hasChecked: String,  
         },
    
    setup(props) {
        
        onUpdated(()=> {
            if (props.hasChecked === props.option){
                document.getElementById(`${props.option}`).checked = true;
            }else {
                document.getElementById(`${props.option}`).checked = false;
            }
        })

        onMounted(()=> {
            if (props.hasChecked === props.option){
                document.getElementById(`${props.option}`).checked = true;
            }else {
                document.getElementById(`${props.option}`).checked = false;
            }
        })
        
        
        return{}
    },
}
</script>


<style scoped>
    input:checked ~ .checked-mark{
        @apply bg-pink-600; 
    }
    

    .checked-mark::after{
        content: "";
        @apply absolute w-1 h-1 rounded-full bg-gray-50 hidden top-1/2 left-1/2 transform -translate-y-1/2 -translate-x-1/2;
    }input:checked ~ .checked-mark::after{
        @apply block; 
    }
</style>