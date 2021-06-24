<template>

    <div class="flex items-center justify-center" >
        <div class="z-10 w-20 text-red-200" id="hour-glass-animation">
       
    </div>
    <p class="relative text-2xl font-bold text-red-200 transform -translate-x-1/4">{{timeDisplay}}</p>
    </div>
    

    
</template>

<script>
import lottie from "lottie-web"; 
import LottieAnimation from 'lottie-vuejs'
import {onMounted, ref, computed} from 'vue';
import  AnimationData from '../assets/hour-glass-animation.json';
export default {
    components: {LottieAnimation},
    setup(props, {emit}) {

        const QUIZ_DURATION = ref(10); //in seconds
    
        const timeDisplay = computed(() => 
            `${parseInt(QUIZ_DURATION.value/60)}:${QUIZ_DURATION.value%60}s`
        )
        
        
        onMounted(() =>{
            
            lottie.loadAnimation({
                container: document.getElementById("hour-glass-animation"), 
                renderer: "svg",
                loop: true,
                autoplay: true,
                //path: "https://assets7.lottiefiles.com/packages/lf20_xz75lvff.json",
                animationData: AnimationData,
            })
        

            let timeInterval = setInterval(()=> {
                if (QUIZ_DURATION.value>0){
                    QUIZ_DURATION.value -=1;
                }else{
                    emit("toSubmit", true);
                }
                

            }, 1000) 
        
        })


        
        return {timeDisplay}


    },
}
</script>