import { Spedizione } from './spedizione';
import { LoginResponseDto } from './login-response-dto';

export class SpedizioneSearchDto extends LoginResponseDto{
  searchKey: string;
  numeroPagina: number;
}
