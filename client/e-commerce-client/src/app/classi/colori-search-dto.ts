import { LoginResponseDto } from './login-response-dto';

export class ColoriSearchDto extends LoginResponseDto {
  searchKey: string;
  numeroPagina: number;
}
