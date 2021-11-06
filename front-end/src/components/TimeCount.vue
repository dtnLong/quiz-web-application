<template>

    <div class="flex items-center justify-center gap-8" >
        <div class="w-10 text-error-800" id="hour-glass-animation"></div>
        <p class="relative text-4xl font-bold tracking-wide transform select-none text-error-800 -translate-x-1/4">{{timeDisplay}}</p>
    </div>
    

    
</template>

<script>
import lottie from "lottie-web"; 
import LottieAnimation from 'lottie-vuejs'
import {onMounted, ref, computed} from 'vue';
import  AnimationData from '../assets/hour-glass-animation.json';
export default {
    props: {
        isBtnSubmitted: {
            type: Boolean,
            default: false
        },
     },
    components: {LottieAnimation},
    emits: ['toSubmit'],
    setup(props, {emit}) {

        const QUIZ_DURATION = ref(1800); //in seconds
    
        const timeDisplay = computed(() => 
            {
                const minutes = String(parseInt(QUIZ_DURATION.value/60));
                const seconds = String(QUIZ_DURATION.value % 60);
                const paddedMinutes = ("0" + minutes).slice(-2);
                const paddedSeconds = ("0" + seconds).slice(-2);
                return `${paddedMinutes}:${paddedSeconds}`;
            }
           
        )
        
        
        onMounted(() =>{
            
            lottie.loadAnimation({
                container: document.getElementById("hour-glass-animation"), 
                renderer: "svg",
                loop: true,
                autoplay: true,
                
                animationData: AnimationData,
            })
            
            
            let preSec = Date.now();

            function countTime() {
                let millis = Date.now() - preSec;
                QUIZ_DURATION.value = (1800 - Math.floor(millis / 1000));
                if (QUIZ_DURATION.value>0 && (!props.isBtnSubmitted)) {
                    setTimeout(countTime, 1000);
                }else if (QUIZ_DURATION.value == 0 && (!props.isBtnSubmitted)){
                    emit("toSubmit", true);
                }

                
            }
            setTimeout(countTime, 1000);

            
                
                // let timeInterval = setInterval(()=> {
                //     //During the submit process, stop the timer.
                //     if (props.isBtnSubmitted){
                //         clearInterval(timeInterval);
                //         return;
                //     } 
                    
                //     if (QUIZ_DURATION.value>0){
                //         let millis = Date.now() - preSec;
                //         preSec = Date.now();
                //         if (millis >=1000){
                //             QUIZ_DURATION.value -= Math.floor(millis / 1000);
                //         }
                        
                //         // console.log(QUIZ_DURATION.value);
                //     }else{

                //         emit("toSubmit", true);
                //         clearInterval(timeInterval);
                //     }
                

                // }, 1000) 

                
            
            
        
        })


        
        return {timeDisplay}


    },
}
</script>