import { LoginResponseDto } from './login-response-dto';
import { Spedizione } from './spedizione';

export class SpedizioneCreateDto extends LoginResponseDto {
  dati: Spedizione;
}
