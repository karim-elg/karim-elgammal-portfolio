/* USER CODE BEGIN Header */
/**
  ******************************************************************************
  * @file           : main.c
  * @brief          : Main program body
  ******************************************************************************
  * @attention
  *
  * Copyright (c) 2023 STMicroelectronics.
  * All rights reserved.
  *
  * This software is licensed under terms that can be found in the LICENSE file
  * in the root directory of this software component.
  * If no LICENSE file comes with this software, it is provided AS-IS.
  *
  ******************************************************************************
  */
/* USER CODE END Header */
/* Includes ------------------------------------------------------------------*/
#include "main.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */
#include <float.h>
#include <stdio.h>
#include "arm_math.h"
#include "measurements.h"
/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */

/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */
#define ITM_Port32(n) (*((volatile unsigned long *) (0xE0000000+4*n)))
#define ARM_MATH_CM4
#define KALMAN_CMSIS
/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */

/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/
UART_HandleTypeDef huart1;

/* USER CODE BEGIN PV */

/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
void SystemClock_Config(void);
static void MX_GPIO_Init(void);
static void MX_USART1_UART_Init(void);
/* USER CODE BEGIN PFP */
#ifdef __GNUC__
#define PUTCHAR_PROTOTYPE int __io_putchar(int ch)
#else
#define PUTCHAR_PROTOTYPE int fputc(int ch, FILE *f)
#endif

PUTCHAR_PROTOTYPE
{
  HAL_UART_Transmit(&huart1, (uint8_t *)&ch, 1, HAL_MAX_DELAY);
  return ch;
}
#ifdef KALMAN_ASSEMBLY

//void KalmanFilter_init(struct kalman_state *filter, float q, float r, float p, float k, float initial_value) {
//    self->q = q;
//    self->r = r;
//    self->p = p;
//    self->x = initial_value;
//}


extern uint8_t kalman(struct kalman_state *filter, float x);

int KalmanFilter(float *InputArray, float *OutputArray, struct kalman_state *kstate, int length)
{


	// These variables will not change in loop
	for(int i=0; i<length; ++i)
	{
		float measurement = InputArray[i];
		int err_code = kalman(kstate, measurement);
		if (err_code != 0)
		{
			return err_code;
		}
		OutputArray[i] = kstate->x; // Set output if no errors
	}
	return 0;
}
#endif

/* USER CODE END PFP */

/* Private user code ---------------------------------------------------------*/
/* USER CODE BEGIN 0 */
uint32_t CheckFloatError(void)
{
	return __get_FPSCR()&0xF; // Check last 4 bits of FPSCR
}

#ifdef KALMAN_CMSIS
void AnalyzeFilter(float *InputArray, float *OutputArray, int length)
{
	float difference[length];
	arm_sub_f32(InputArray, OutputArray, difference, length);
	float mean, std;
	arm_mean_f32(difference, length, &mean); // Find mean of the difference
	arm_std_f32(difference, length, &std); // Standard deviation of the difference
	float correlation[2*length-1], conv[2*length-1]; // Both end up same length
	arm_correlate_f32(InputArray, length, OutputArray, length, correlation);
	arm_conv_f32(InputArray, length, OutputArray, length, conv);
}
#endif

#ifdef KALMAN_PLAIN_C

void FindDifference(float *array1, float *array2, float *difference, int length)
{
	for (int i=0; i<length; ++i)
	{
		difference[i] = array1[i] - array2[i]; // Elementwise subtraction
	}
}

float FindMean(float *array, int length)
{
	float sum;
	for (int i=0; i<length; i++)
	{
		sum += array[i];
	}
	return sum/(float)length; // Compute average
}

float FindStd(float *array, float mean, int length)
{
	float squared_sum;
	for (int i=0; i<length; i++)
	{
		float diff = array[i]-mean;
		squared_sum += diff*diff; // Square of difference
	}
	float std = sqrt(squared_sum/(length-1)); // divide
	return std;
}


void Correlate(float *array1, float *array2, float *correlation, int length)
{
//Correlation according to the CMSIS library
	  for (int i = 0; i < (2*length)-1; i++)
	  {
		  correlation[i] = 0; // Need to set this manually
		  for(int j = 0; j <= i; j++)
		  {
			  if (j<length && length-1-i+j>=0)
			  {
				  // Opposite of convolution in time
				  correlation[i] +=  array1[j]*array2[length-1-i+j];
			  }
		  }
	  }
}

void Convolute(float *array1, float *array2, float *convolution, int length)
{
//Convolution according to the CMSIS library

		  for (int i = 0; i < (2*length)-1; i++)
		  {
			  convolution[i] = 0;
			  for(int j = 0; j <= i; j++)
			  {
				  if (j<length && i-j<length)
				  {
					  convolution[i] +=  array1[j]*array2[i-j];
				  }
			  }
		  }
}

void AnalyzeFilter(float *InputArray, float *OutputArray, int length)
{
	float difference[length];
	FindDifference(InputArray, OutputArray, difference, length);
	float mean = FindMean(difference, length);
	float std = FindStd(difference, mean, length);
	float correlation[2*length-1], conv[2*length-1]; // Both end up same length
	Correlate(InputArray, OutputArray, correlation, length);
	Convolute(InputArray, OutputArray, conv, length);
	return;

}
#endif

#if defined(KALMAN_CMSIS) || defined(KALMAN_PLAIN_C)
int KalmanFilter(float *InputArray, float *OutputArray, struct kalman_state *kstate, int length)
{
	// These variables will not change in loop
	for(int i=0; i<length; ++i)
	{
		kstate->p += kstate->q; // self.p = self.p + self.q
		if (kstate->p+kstate->r==0) {
			return -1;
		}
		kstate->k = kstate->p / (kstate->p+kstate->r); // self.k = self.p/(self.p+self.r)
		kstate->x += kstate->k * (InputArray[i]-kstate->x); // x = x+k*(measurement-x)
		kstate->p -= kstate->p*kstate->k;
		if (CheckFloatError() != 0)
		{
			return CheckFloatError(); // Return and exit function
		}
		OutputArray[i] = kstate->x; // Set output if no errors
	}
	AnalyzeFilter(InputArray, OutputArray, length);
	return 0;
}
#endif

/* USER CODE END 0 */

/**
  * @brief  The application entry point.
  * @retval int
  */
int main(void)
{
  /* USER CODE BEGIN 1 */
	struct kalman_state filter;
	filter.q = process_variance;
	filter.r = measurement_variance;
	filter.p = 0.0;
	//filter.q = FLT_MAX;
	//filter.p = FLT_MAX;
	filter.k = 0.0;
	filter.x = 0.0;
	uint64_t count = 0; // count for Kalman test

  /* USER CODE END 1 */

  /* MCU Configuration--------------------------------------------------------*/

  /* Reset of all peripherals, Initializes the Flash interface and the Systick. */
  HAL_Init();

  /* USER CODE BEGIN Init */

  /* USER CODE END Init */

  /* Configure the system clock */
  SystemClock_Config();

  /* USER CODE BEGIN SysInit */

  /* USER CODE END SysInit */

  /* Initialize all configured peripherals */
  MX_GPIO_Init();
  MX_USART1_UART_Init();
  /* USER CODE BEGIN 2 */
  setbuf(stdin, NULL);

  float OutputArray[100];

  ITM_Port32(31) = 1;
  KalmanFilter(measurements, OutputArray, &filter, 100);
  ITM_Port32(31) = 2;

  /* USER CODE END 2 */

  /* Infinite loop */
  /* USER CODE BEGIN WHILE */
  while (1)
  {
    /* USER CODE END WHILE */

    /* USER CODE BEGIN 3 */
  }
  /* USER CODE END 3 */
}

/**
  * @brief System Clock Configuration
  * @retval None
  */
void SystemClock_Config(void)
{
  RCC_OscInitTypeDef RCC_OscInitStruct = {0};
  RCC_ClkInitTypeDef RCC_ClkInitStruct = {0};

  /** Configure the main internal regulator output voltage
  */
  if (HAL_PWREx_ControlVoltageScaling(PWR_REGULATOR_VOLTAGE_SCALE1_BOOST) != HAL_OK)
  {
    Error_Handler();
  }

  /** Initializes the RCC Oscillators according to the specified parameters
  * in the RCC_OscInitTypeDef structure.
  */
  RCC_OscInitStruct.OscillatorType = RCC_OSCILLATORTYPE_MSI;
  RCC_OscInitStruct.MSIState = RCC_MSI_ON;
  RCC_OscInitStruct.MSICalibrationValue = 0;
  RCC_OscInitStruct.MSIClockRange = RCC_MSIRANGE_6;
  RCC_OscInitStruct.PLL.PLLState = RCC_PLL_ON;
  RCC_OscInitStruct.PLL.PLLSource = RCC_PLLSOURCE_MSI;
  RCC_OscInitStruct.PLL.PLLM = 1;
  RCC_OscInitStruct.PLL.PLLN = 60;
  RCC_OscInitStruct.PLL.PLLP = RCC_PLLP_DIV2;
  RCC_OscInitStruct.PLL.PLLQ = RCC_PLLQ_DIV2;
  RCC_OscInitStruct.PLL.PLLR = RCC_PLLR_DIV2;
  if (HAL_RCC_OscConfig(&RCC_OscInitStruct) != HAL_OK)
  {
    Error_Handler();
  }

  /** Initializes the CPU, AHB and APB buses clocks
  */
  RCC_ClkInitStruct.ClockType = RCC_CLOCKTYPE_HCLK|RCC_CLOCKTYPE_SYSCLK
                              |RCC_CLOCKTYPE_PCLK1|RCC_CLOCKTYPE_PCLK2;
  RCC_ClkInitStruct.SYSCLKSource = RCC_SYSCLKSOURCE_PLLCLK;
  RCC_ClkInitStruct.AHBCLKDivider = RCC_SYSCLK_DIV1;
  RCC_ClkInitStruct.APB1CLKDivider = RCC_HCLK_DIV1;
  RCC_ClkInitStruct.APB2CLKDivider = RCC_HCLK_DIV1;

  if (HAL_RCC_ClockConfig(&RCC_ClkInitStruct, FLASH_LATENCY_5) != HAL_OK)
  {
    Error_Handler();
  }
}

/**
  * @brief USART1 Initialization Function
  * @param None
  * @retval None
  */
static void MX_USART1_UART_Init(void)
{

  /* USER CODE BEGIN USART1_Init 0 */

  /* USER CODE END USART1_Init 0 */

  /* USER CODE BEGIN USART1_Init 1 */

  /* USER CODE END USART1_Init 1 */
  huart1.Instance = USART1;
  huart1.Init.BaudRate = 115200;
  huart1.Init.WordLength = UART_WORDLENGTH_8B;
  huart1.Init.StopBits = UART_STOPBITS_1;
  huart1.Init.Parity = UART_PARITY_NONE;
  huart1.Init.Mode = UART_MODE_TX_RX;
  huart1.Init.HwFlowCtl = UART_HWCONTROL_NONE;
  huart1.Init.OverSampling = UART_OVERSAMPLING_16;
  huart1.Init.OneBitSampling = UART_ONE_BIT_SAMPLE_DISABLE;
  huart1.Init.ClockPrescaler = UART_PRESCALER_DIV1;
  huart1.AdvancedInit.AdvFeatureInit = UART_ADVFEATURE_NO_INIT;
  if (HAL_UART_Init(&huart1) != HAL_OK)
  {
    Error_Handler();
  }
  if (HAL_UARTEx_SetTxFifoThreshold(&huart1, UART_TXFIFO_THRESHOLD_1_8) != HAL_OK)
  {
    Error_Handler();
  }
  if (HAL_UARTEx_SetRxFifoThreshold(&huart1, UART_RXFIFO_THRESHOLD_1_8) != HAL_OK)
  {
    Error_Handler();
  }
  if (HAL_UARTEx_DisableFifoMode(&huart1) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN USART1_Init 2 */

  /* USER CODE END USART1_Init 2 */

}

/**
  * @brief GPIO Initialization Function
  * @param None
  * @retval None
  */
static void MX_GPIO_Init(void)
{

  /* GPIO Ports Clock Enable */
  __HAL_RCC_GPIOA_CLK_ENABLE();
  __HAL_RCC_GPIOB_CLK_ENABLE();

}

/* USER CODE BEGIN 4 */

/* USER CODE END 4 */

/**
  * @brief  This function is executed in case of error occurrence.
  * @retval None
  */
void Error_Handler(void)
{
  /* USER CODE BEGIN Error_Handler_Debug */
  /* User can add his own implementation to report the HAL error return state */
  __disable_irq();
  while (1)
  {
  }
  /* USER CODE END Error_Handler_Debug */
}

#ifdef  USE_FULL_ASSERT
/**
  * @brief  Reports the name of the source file and the source line number
  *         where the assert_param error has occurred.
  * @param  file: pointer to the source file name
  * @param  line: assert_param error line source number
  * @retval None
  */
void assert_failed(uint8_t *file, uint32_t line)
{
  /* USER CODE BEGIN 6 */
  /* User can add his own implementation to report the file name and line number,
     ex: printf("Wrong parameters value: file %s on line %d\r\n", file, line) */
  /* USER CODE END 6 */
}
#endif /* USE_FULL_ASSERT */
